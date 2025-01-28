package com.hms.hms.services;

import com.hms.hms.models.Appointment;
import com.hms.hms.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AppointmentService {

    private static final Logger logger = LogManager.getLogger(AppointmentService.class);

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Appointment appointment) {
        try {
            logger.info("Saving new appointment");
            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            logger.error("Error while saving appointment: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Appointment> getAllAppointments() {
        try {
            logger.info("Fetching all appointments");
            return appointmentRepository.findAll();
        } catch (Exception e) {
            logger.error("Error while fetching appointments: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Appointment getAppointmentById(Long id) {
        try {
            logger.info("Fetching appointment with ID: {}", id);
            return appointmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
        } catch (Exception e) {
            logger.error("Error while fetching appointment with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public Appointment updateAppointment(Long id, Appointment appointment) {
        try {
            logger.info("Updating appointment with ID: {}", id);
            Appointment existingAppointment = appointmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
            existingAppointment.setPatientId(appointment.getPatientId());
            existingAppointment.setDoctorId(appointment.getDoctorId());
            existingAppointment.setDate(appointment.getDate());
            existingAppointment.setTime(appointment.getTime());
            existingAppointment.setStatus(appointment.getStatus());
            return appointmentRepository.save(existingAppointment);
        } catch (Exception e) {
            logger.error("Error while updating appointment with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public void deleteAppointment(Long id) {
        try {
            logger.info("Deleting appointment with ID: {}", id);
            if (!appointmentRepository.existsById(id)) {
                throw new RuntimeException("Appointment not found with ID: " + id);
            }
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error while deleting appointment with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
