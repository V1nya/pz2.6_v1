package com.example.pz2_6_v1.modal;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)

    private String name;
    @Column(nullable = false)

    private String login;
    @Column(nullable = false)

    private String password;
    @Column(nullable = false)

    private String email;

    // Constructors, getters, setters
}

