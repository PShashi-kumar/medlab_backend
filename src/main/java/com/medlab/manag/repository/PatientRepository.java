package com.medlab.manag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medlab.manag.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByEmail(String email);
    Patient findByEmailAndPassword(String email, String password);
}