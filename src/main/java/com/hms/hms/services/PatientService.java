package com.hms.hms.services;

import com.hms.hms.models.Patient;
import com.hms.hms.repositories.PatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static final Logger logger = LogManager.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        try {
            logger.info("Fetching all patients");
            return patientRepository.findAll();
        } catch (Exception e) {
            logger.error("Error while fetching all patients: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Patient getPatientById(Long id) {
        try {
            logger.info("Fetching patient by ID: {}", id);
            return patientRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Patient not found with ID: {}", id);
                        return new RuntimeException("Patient not found with ID: " + id);
                    });
        } catch (Exception e) {
            logger.error("Error while fetching patient by ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public Patient createPatient(Patient patient) {
        try {
            logger.info("Creating a new patient");
            return patientRepository.save(patient);
        } catch (Exception e) {
            logger.error("Error while creating patient: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Patient updatePatient(Long id, Patient patient) {
        try {
            logger.info("Updating patient with ID: {}", id);
            Patient existingPatient = patientRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Patient not found with ID: {}", id);
                        return new RuntimeException("Patient not found with ID: " + id);
                    });

            existingPatient.setName(patient.getName());
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setPhone(patient.getPhone());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setGender(patient.getGender());

            return patientRepository.save(existingPatient);
        } catch (Exception e) {
            logger.error("Error while updating patient with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public void deletePatient(Long id) {
        try {
            logger.info("Deleting patient with ID: {}", id);
            if (!patientRepository.existsById(id)) {
                logger.warn("Patient not found with ID: {}", id);
                throw new RuntimeException("Patient not found with ID: " + id);
            }
            patientRepository.deleteById(id);
            logger.info("Successfully deleted patient with ID: {}", id);
        } catch (Exception e) {
            logger.error("Error while deleting patient with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
