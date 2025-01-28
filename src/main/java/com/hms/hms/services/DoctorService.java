package com.hms.hms.services;

import com.hms.hms.models.Doctor;
import com.hms.hms.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorService  {

    private static final Logger logger = LogManager.getLogger(DoctorService.class);

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        try {
            Doctor savedDoctor = doctorRepository.save(doctor);
            logger.info("Doctor created successfully: {}", savedDoctor);
            return savedDoctor;
        } catch (Exception e) {
            logger.error("Error creating doctor: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create doctor", e);
        }
    }

    public List<Doctor> getAllDoctors() {
        try {
            logger.info("Fetching all doctors");
            return doctorRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching doctors: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch doctors", e);
        }
    }
    @Cacheable(value = "doctor", key = "#id")
    public Doctor getDoctorById(Long id) {
        try {
            logger.info("Fetching doctor with ID: {}", id);
            Optional<Doctor> doctor = doctorRepository.findById(id);
            return doctor.orElseThrow(() -> {
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
            Doctor updatedDoctor = doctorRepository.save(existingDoctor);
            logger.info("Doctor updated successfully with ID: {}", id);
            return updatedDoctor;
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
            doctorRepository.delete(doctor);
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
