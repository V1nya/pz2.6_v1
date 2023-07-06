package com.example.pz2_6_v1.modal;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "diagnoses")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String diagnosis;


    private LocalDate diagnosisDate;

    // Constructors, getters, setters
}
