package com.example.concertapplication.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;



    @Getter
    @Setter
    @Column(name = "price")
    private Double price;

    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    private User user;

    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="concerts_id")
    private Concert concert;

    @Getter
    @Setter
    private Integer places;

    public Ticket(Double price, User user, Concert concert,Integer places) {
        this.price=price;
        this.user=user;
        this.concert=concert;
        this.places=places;
    }
}
