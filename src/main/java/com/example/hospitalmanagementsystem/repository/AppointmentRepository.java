package com.example.hospitalmanagementsystem.repository;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends  JpaRepository<Appointment  , Integer>
{  List <Appointment> findByPatient(Patient patient);

    List <Appointment> findByDoctor(Doctor doctor);
      List <Appointment> findByStatus(String status);
}