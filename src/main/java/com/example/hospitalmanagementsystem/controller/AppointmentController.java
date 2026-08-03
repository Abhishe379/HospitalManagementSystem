package com.example.hospitalmanagementsystem.controller;
import com.example.hospitalmanagementsystem.dto.AppointmentDTO;
import com.example.hospitalmanagementsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController
{
    @Autowired
    private AppointmentService service;
    @PostMapping
    public AppointmentDTO appointmentDTO(@RequestBody AppointmentDTO dto)
    {
        return service.addAppointment(dto);
    }
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
    public AppointmentDTO updateAppointment(@RequestBody AppointmentDTO dto)
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
}
