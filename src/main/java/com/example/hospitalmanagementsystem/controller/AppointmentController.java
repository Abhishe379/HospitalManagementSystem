package com.example.hospitalmanagementsystem.controller;
import com.example.hospitalmanagementsystem.dto.AppointmentDTO;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController
{
    @Autowired
    private AppointmentService service;

    @GetMapping
    public List<AppointmentDTO> getAllAppointments()
    {
        return service.getAllAppointments();
    }
    @GetMapping("/{id}")
    public  AppointmentDTO getAppointmentById(@PathVariable int id)
    {
        return  service.getAppointmentById(id);
    }
    @PutMapping
    public AppointmentDTO updateAppointment( @Valid @RequestBody AppointmentDTO dto)
    {
        return service.updateAppointment(dto);
    }
    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable int id)
    {
        return service.deleteAppointment(id);
    }
    @GetMapping("/patient/{patientId}")
    public List<AppointmentDTO> getAppointmentByPatient(@PathVariable int patientId)
    {
        return service.getAppointmentByPatient(patientId);
    }
    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDTO> getAppointmentByDoctor(@PathVariable int doctorId)
    {
        return service.getAppointmentByDoctor(doctorId);
    }
    @GetMapping("/status/{status}")
    public List<AppointmentDTO> getAppointmentByStatus(@PathVariable String status)
    {
        return service.getAppointmentByStatus(status);
    }
    @PostMapping
    public AppointmentDTO addAppointment(@Valid @RequestBody AppointmentDTO dto)
    {
        return service.addAppointment(dto);
    }

    @GetMapping("/page")
    public Page<Appointment> getAppointmentsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "appointmentDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.getAppointmentsWithPaginationAndSorting(
                page,
                size,
                sortBy,
                direction
        );
    }
    @GetMapping("/status")
    public Page<Appointment> searchAppointmentsByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "appointmentDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchAppointmentsByStatus(
                status,
                page,
                size,
                sortBy,
                direction
        );
    }
    @GetMapping("/patient")
    public Page<Appointment> searchAppointmentsByPatient(
            @RequestParam int patientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "appointmentDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchAppointmentsByPatient(
                patientId,
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/doctor")
    public Page<Appointment> searchAppointmentsByDoctor(
            @RequestParam int doctorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "appointmentDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchAppointmentsByDoctor(
                doctorId,
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/date")
    public Page<Appointment> searchAppointmentsByDate(
            @RequestParam LocalDate appointmentDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "appointmentDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchAppointmentsByDate(
                appointmentDate,
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/date-range")
    public Page<Appointment> searchAppointmentsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "appointmentDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchAppointmentsByDateRange(
                startDate,
                endDate,
                page,
                size,
                sortBy,
                direction
        );
    }
    @PutMapping("/{id}/status")
    public AppointmentDTO updateAppointmentStatus(
            @PathVariable int id,
            @RequestParam String status
    ) {
        return service.updateAppointmentStatus(id, status);
    }
}
