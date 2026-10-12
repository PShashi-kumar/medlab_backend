package com.medlab.manag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medlab.manag.entity.Patient;
import com.medlab.manag.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public boolean emailExists(String email) {
        return patientRepository.findByEmail(email) != null;
    }

    public Patient register(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient login(String email, String password) {
        return patientRepository.findByEmailAndPassword(email, password);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}