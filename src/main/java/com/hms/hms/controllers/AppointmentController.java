package com.hms.hms.controllers;

import com.hms.hms.models.Appointment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    // Creates a new appointment record
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        System.out.println("Create appointment");
        return null;
    }

    // Retrieves a list of all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        System.out.println("Get all appointments");
        return null;
    }

    // Retrieves a specific appointment's details by its ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        System.out.println("Get appointment by ID: " + id);
        return null;
    }

    // Updates an existing appointment's details by its ID
    @PutMapping("/{id}")
    public void updateAppointment(@PathVariable Long id) {
        System.out.println("Update appointment by ID: " + id);
    }

    // Deletes a specific appointment's record by its ID
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        System.out.println("Delete appointment by ID: " + id);
    }
}

