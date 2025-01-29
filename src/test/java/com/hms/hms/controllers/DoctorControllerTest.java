package com.hms.hms.controllers;

import com.hms.hms.models.Doctor;
import com.hms.hms.services.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. John Doe");
        doctor.setSpecialization("Cardiology");
    }

    @Test
    void createDoctor() {
        when(doctorService.createDoctor(any(Doctor.class))).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.createDoctor(doctor);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(doctor.getId(), response.getBody().getId());
        assertEquals(doctor.getName(), response.getBody().getName());
        assertEquals(doctor.getSpecialization(), response.getBody().getSpecialization());

        verify(doctorService, times(1)).createDoctor(any(Doctor.class));
    }

    @Test
    void getAllDoctors() {
        List<Doctor> doctors = Arrays.asList(doctor);
        when(doctorService.getAllDoctors()).thenReturn(doctors);

        ResponseEntity<List<Doctor>> response = doctorController.getAllDoctors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(doctor.getId(), response.getBody().get(0).getId());

        verify(doctorService, times(1)).getAllDoctors();
    }

    @Test
    void getDoctorById() {
        when(doctorService.getDoctorById(1L)).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.getDoctorById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(doctor.getId(), response.getBody().getId());
        assertEquals(doctor.getName(), response.getBody().getName());
        assertEquals(doctor.getSpecialization(), response.getBody().getSpecialization());

        verify(doctorService, times(1)).getDoctorById(1L);
    }

    @Test
    void getDoctorById_NotFound() {
        when(doctorService.getDoctorById(1L)).thenThrow(new RuntimeException("Doctor not found"));

        ResponseEntity<Doctor> response = doctorController.getDoctorById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(doctorService, times(1)).getDoctorById(1L);
    }

    @Test
    void updateDoctor() {
        when(doctorService.updateDoctor(1L, doctor)).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.updateDoctor(1L, doctor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(doctor.getId(), response.getBody().getId());
        assertEquals(doctor.getName(), response.getBody().getName());
        assertEquals(doctor.getSpecialization(), response.getBody().getSpecialization());

        verify(doctorService, times(1)).updateDoctor(1L, doctor);
    }

    @Test
    void updateDoctor_NotFound() {
        when(doctorService.updateDoctor(1L, doctor)).thenThrow(new RuntimeException("Doctor not found"));

        ResponseEntity<Doctor> response = doctorController.updateDoctor(1L, doctor);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(doctorService, times(1)).updateDoctor(1L, doctor);
    }

    @Test
    void deleteDoctor() {
        doNothing().when(doctorService).deleteDoctor(1L);

        ResponseEntity<Void> response = doctorController.deleteDoctor(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(doctorService, times(1)).deleteDoctor(1L);
    }

    @Test
    void deleteDoctor_NotFound() {
        doThrow(new RuntimeException("Doctor not found")).when(doctorService).deleteDoctor(1L);

        ResponseEntity<Void> response = doctorController.deleteDoctor(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(doctorService, times(1)).deleteDoctor(1L);
    }
}