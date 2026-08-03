package com.example.hospitalmanagementsystem.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment
{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
   private LocalDate appointmentDate;
   private String status;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
   @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

   public Appointment()
   {

   }
   public Appointment(int id , LocalDate appointmentDate , String status , Patient patient  ,  Doctor  doctor)
   {
       this.id = id;
       this.appointmentDate = appointmentDate;
       this.status = status;
       this.patient = patient;
       this.doctor = doctor;
   }
   public int getId()
   {
       return id;
   }

    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getAppointmentDate()
    {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
