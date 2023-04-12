package com.example.concertapplication.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Setter
    @Getter
    @Column(name = "last_name")
    private  String lastName;

    @Getter
    @Setter
    @Column(name= "phone_number")
    private String phoneNumber;

    @Getter
    @Setter
    @Column(name="authority")
    private String authority;

    @Getter
    @Setter
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "tickets",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "concerts_id")}
    )
    private Set<Concert> concerts= new HashSet<>();

}