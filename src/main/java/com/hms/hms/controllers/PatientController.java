package com.hms.hms.controllers;

import com.hms.hms.models.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    // Creates a new patient record
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        System.out.println("Create patient");
        return null;
    }

    // Retrieves a list of all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        System.out.println("Get all patients");
        return null;
    }

    // Retrieves a specific patient's details by their ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        System.out.println("Get patient by ID: " + id);
        return null;
    }

    // Updates an existing patient's details by their ID
    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id) {
        System.out.println("Update patient by ID: " + id);
    }

    // Deletes a specific patient's record by their ID
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        System.out.println("Delete patient by ID: " + id);
    }
}
