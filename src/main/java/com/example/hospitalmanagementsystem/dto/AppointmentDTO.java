package com.example.hospitalmanagementsystem.dto;

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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public String getStatus()
    {
        return  status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
