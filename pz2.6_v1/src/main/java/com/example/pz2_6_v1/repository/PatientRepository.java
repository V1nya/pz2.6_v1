package com.example.pz2_6_v1.repository;

import com.example.pz2_6_v1.modal.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
