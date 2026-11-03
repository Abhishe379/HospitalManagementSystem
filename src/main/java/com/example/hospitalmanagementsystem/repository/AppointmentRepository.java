package com.example.hospitalmanagementsystem.repository;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends  JpaRepository<Appointment  , Integer>
{  List <Appointment> findByPatient(Patient patient);

    List <Appointment> findByDoctor(Doctor doctor);
      List <Appointment> findByStatus(String status);
     // boolean existsByDoctorAndAppointmentDate(Doctor doctor , LocalDate appointmentDate);
     boolean existsByPatientAndDoctorAndAppointmentDate(
             Patient patient,
             Doctor doctor,
             LocalDate appointmentDate);

    boolean existsByPatientAndDoctorAndAppointmentDateAndIdNot(
            Patient patient,
            Doctor doctor,
            LocalDate appointmentDate,
            Integer id);

    Page<Appointment> findByStatusContainingIgnoreCase(
            String status,
            Pageable pageable
    );
    Page<Appointment> findByPatientId (int patientId , Pageable pageable);

    Page<Appointment> findByDoctorId(
            int doctorId,
            Pageable pageable
    );

    Page<Appointment> findByAppointmentDate(
            LocalDate appointmentDate,
            Pageable pageable
    );

    Page<Appointment> findByAppointmentDateBetween(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );
}