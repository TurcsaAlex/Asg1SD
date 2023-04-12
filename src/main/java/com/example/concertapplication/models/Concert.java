package com.example.concertapplication.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "concerts")
@AllArgsConstructor
@NoArgsConstructor
public class Concert {
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
    @Column
    private int seats;

    @Getter
    @Setter
    @Column
    private Time date;

    @Getter
    @Setter
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "concerts")

    private Set<Band> bands= new HashSet<>();



    @Getter
    @Setter
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "concerts")

    private Set<User> users =new HashSet<>();
    public Concert(String name, int seats, Time date) {
        this.name = name;
        this.seats = seats;
        this.date = date;
    }
    public void addBand(Band band){
        bands.add(band);
    }
}
