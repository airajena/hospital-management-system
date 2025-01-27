package com.hms.hms.controllers;

import com.hms.hms.models.Appointment;
import com.hms.hms.services.AppointmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private static final Logger logger = LogManager.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Creates a new appointment record
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        try {
            logger.info("Creating a new appointment");
            return ResponseEntity.ok(appointmentService.createAppointment(appointment));
        } catch (Exception e) {
            logger.error("Error while creating appointment: {}", e.getMessage(), e);
            throw e;
        }
    }

    // Retrieves a list of all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        try {
            logger.info("Retrieving all appointments");
            return ResponseEntity.ok(appointmentService.getAllAppointments());
        } catch (Exception e) {
            logger.error("Error while retrieving appointments: {}", e.getMessage(), e);
            throw e;
        }
    }

    // Retrieves a specific appointment's details by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        try {
            logger.info("Retrieving appointment by ID: {}", id);
            return ResponseEntity.ok(appointmentService.getAppointmentById(id));
        } catch (Exception e) {
            logger.error("Error while retrieving appointment with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    // Updates an existing appointment's details by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        try {
            logger.info("Updating appointment with ID: {}", id);
            return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment));
        } catch (Exception e) {
            logger.error("Error while updating appointment with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    // Deletes a specific appointment's record by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            logger.info("Deleting appointment with ID: {}", id);
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error while deleting appointment with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
