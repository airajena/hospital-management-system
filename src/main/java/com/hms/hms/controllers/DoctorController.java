package com.hms.hms.controllers;

import com.hms.hms.models.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    // Creates a new doctor record
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        System.out.println("Create doctor");
        return null;
    }

    // Retrieves a list of all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        System.out.println("Get all doctors");
        return null;
    }

    // Retrieves a specific doctor's details by their ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        System.out.println("Get doctor by ID: " + id);
        return null;
    }

    // Updates an existing doctor's details by their ID
    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable Long id) {
        System.out.println("Update doctor by ID: " + id);
    }

    // Deletes a specific doctor's record by their ID
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        System.out.println("Delete doctor by ID: " + id);
    }
}
