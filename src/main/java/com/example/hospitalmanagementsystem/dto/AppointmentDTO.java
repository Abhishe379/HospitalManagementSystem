package com.example.hospitalmanagementsystem.dto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class AppointmentDTO
{
    private  Integer id;
    private LocalDate appointmentDate;
    private  String status;
    private Integer patientId;
    private Integer doctorId;

    public AppointmentDTO()
    {

    }
    public AppointmentDTO( Integer id  ,  LocalDate appointmentDate , String status , Integer patientId , Integer doctorId)
    {
        this.id=id;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
@NotNull(message = "Appointment date is required")
@FutureOrPresent(message = "Appointment date cannot be in the past ")
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    @NotBlank(message = "Status is required")
    public String getStatus()
    {
        return  status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotNull(message = "Patient ID is required")
    @Positive(message = "Patient ID must be greater than 0")
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
@NotNull(message = "Doctor ID is required")
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
