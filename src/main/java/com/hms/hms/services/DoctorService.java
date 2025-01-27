package com.hms.hms.services;

import com.hms.hms.models.Doctor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class  DoctorService {

    private static final Logger logger = LogManager.getLogger(DoctorService.class);
    private final List<Doctor> doctors = new ArrayList<>();

    public Doctor createDoctor(Doctor doctor) {
        try {
            doctor.setId((long) (doctors.size() + 1));
            doctors.add(doctor);
            logger.info("Doctor created successfully: {}", doctor);
            return doctor;
        } catch (Exception e) {
            logger.error("Error creating doctor: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create doctor", e);
        }
    }

    public List<Doctor> getAllDoctors() {
        try {
            logger.info("Fetching all doctors");
            return doctors;
        } catch (Exception e) {
            logger.error("Error fetching doctors: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch doctors", e);
        }
    }

    public Doctor getDoctorById(Long id) {
        try {
            logger.info("Fetching doctor with ID: {}", id);
            return doctors.stream()
                    .filter(doctor -> doctor.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> {
                        logger.warn("Doctor not found with ID: {}", id);
                        return new RuntimeException("Doctor not found with ID: " + id);
                    });
        } catch (Exception e) {
            logger.error("Error fetching doctor by ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch doctor by ID: " + id, e);
        }
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {
        try {
            Doctor existingDoctor = getDoctorById(id);
            existingDoctor.setName(doctor.getName());
            existingDoctor.setSpecialization(doctor.getSpecialization());
            logger.info("Doctor updated successfully with ID: {}", id);
            return existingDoctor;
        } catch (RuntimeException e) {
            logger.warn("Doctor not found with ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating doctor with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update doctor with ID: " + id, e);
        }
    }

    public void deleteDoctor(Long id) {
        try {
            Doctor doctor = getDoctorById(id);
            doctors.remove(doctor);
            logger.info("Doctor deleted successfully with ID: {}", id);
        } catch (RuntimeException e) {
            logger.warn("Doctor not found with ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting doctor with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete doctor with ID: " + id, e);
        }
    }
}
