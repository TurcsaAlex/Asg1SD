package com.example.concertapplication.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bands")
@AllArgsConstructor
@NoArgsConstructor
public class Band {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "genre")
    private String genre;

    @Getter
    @Setter
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "concertbands",
            joinColumns = {@JoinColumn(name = "band_id")},
            inverseJoinColumns = {@JoinColumn(name = "concert_id")}
    )
    private Set<Concert> concerts;

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", concerts=" + concerts +
                '}';
    }

    public void addConcert(Concert concert){
        concerts.add(concert);
    }

    public Band(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
}
