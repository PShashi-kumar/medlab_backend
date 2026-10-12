package com.medlab.manag.controller;

import com.medlab.manag.entity.Patient;
import com.medlab.manag.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Patient patient) {
        if (patientService.emailExists(patient.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already registered"));
        }
        return ResponseEntity.ok(patientService.register(patient));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Patient patient = patientService.login(body.get("email"), body.get("password"));
        if (patient == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid email or password"));
        }
        return ResponseEntity.ok(patient);
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientService.getAll();
    }
}