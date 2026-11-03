package com.example.hospitalmanagementsystem.service;
import com.example.hospitalmanagementsystem.dto.AppointmentDTO;
import com.example.hospitalmanagementsystem.exception.DuplicateAppointmentException;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.AppointmentRepository;
import com.example.hospitalmanagementsystem.repository.PatientRepository;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class  AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Appointment convertToEntity(AppointmentDTO dto)
    {
        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStatus(dto.getStatus());

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointment;
    }
    public AppointmentDTO convertToDTO(Appointment appointment)
    {
        AppointmentDTO dto = new AppointmentDTO();

        dto.setId(appointment.getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setStatus(appointment.getStatus());

        dto.setPatientId(appointment.getPatient().getId());
        dto.setDoctorId(appointment.getDoctor().getId());

        return dto;
    }
    public AppointmentDTO addAppointment(AppointmentDTO dto)
    {
        Appointment appointment = convertToEntity(dto);
        boolean exists = appointmentRepository
                .existsByPatientAndDoctorAndAppointmentDate(
                        appointment.getPatient(),
                        appointment.getDoctor(),
                        appointment.getAppointmentDate());

        if (exists) {
            throw new DuplicateAppointmentException(
                    "Duplicate appointment: Patient already has an appointment with this doctor on this date.");
        }

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return convertToDTO(savedAppointment);
    }

    public List<AppointmentDTO> getAllAppointments()
    {
        return appointmentRepository.findAll() .stream().map(this::convertToDTO).toList();
    }
    public AppointmentDTO getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Appointment not found"));
     return convertToDTO(appointment);
    }
    public AppointmentDTO updateAppointment(AppointmentDTO dto)
    {
        // Check whether appointment exists
        Appointment existingAppointment =
                appointmentRepository.findById(dto.getId())
                        .orElseThrow(() ->
                                new RuntimeException("Appointment not found"));

        Appointment appointment = convertToEntity(dto);

        boolean exists =
                appointmentRepository
                        .existsByPatientAndDoctorAndAppointmentDateAndIdNot(
                                appointment.getPatient(),
                                appointment.getDoctor(),
                                appointment.getAppointmentDate(),
                                dto.getId());

        if (exists) {
            throw new DuplicateAppointmentException(
                    "Duplicate appointment: Patient already has an appointment with this doctor on this date.");
        }

        appointment.setId(existingAppointment.getId());

        Appointment updatedAppointment =
                appointmentRepository.save(appointment);

        return convertToDTO(updatedAppointment);
    }

    public String deleteAppointment(int id)
    {
        if(!appointmentRepository.existsById(id))
        {
            throw new RuntimeException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
        return "Appointment Delete Successfully";
    }
    public List<AppointmentDTO> getAppointmentByPatient(int patientId)
    {
        Patient patient = patientRepository.findById(patientId) .orElseThrow(() -> new RuntimeException("Patient not found"));

        return appointmentRepository .findByPatient(patient)
                .stream().map(this::convertToDTO).toList();
    }
    public List<AppointmentDTO> getAppointmentByDoctor(int doctorId)
    {
        Doctor doctor = doctorRepository.findById(doctorId) .orElseThrow(()-> new RuntimeException("Doctor not found"));
         return appointmentRepository.findByDoctor(doctor) .stream().map(this::convertToDTO).toList();
    }
public  List<AppointmentDTO> getAppointmentByStatus(String status)
{
    return appointmentRepository.findByStatus(status)
            .stream()
            .map(this::convertToDTO)
            .toList();
}

    public Page<Appointment> getAppointmentsWithPaginationAndSorting(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return appointmentRepository.findAll(pageable);
    }

    public Page<Appointment> searchAppointmentsByStatus(
            String status,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return appointmentRepository
                .findByStatusContainingIgnoreCase(status, pageable);
    }
    public Page<Appointment> searchAppointmentsByPatient(
            int patientId,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return appointmentRepository.findByPatientId(
                patientId,
                pageable
        );
    }

    public Page<Appointment> searchAppointmentsByDoctor(
            int doctorId,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return appointmentRepository.findByDoctorId(
                doctorId,
                pageable
        );
    }
    public Page<Appointment> searchAppointmentsByDate(
            LocalDate appointmentDate,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return appointmentRepository.findByAppointmentDate(
                appointmentDate,
                pageable
        );
    }

    public Page<Appointment> searchAppointmentsByDateRange(
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return appointmentRepository.findByAppointmentDateBetween(
                startDate,
                endDate,
                pageable
        );
    }
    public AppointmentDTO updateAppointmentStatus(
            int id,
            String status) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment with ID " + id + " not found"
                        )
                );

        appointment.setStatus(status);

        Appointment updatedAppointment =
                appointmentRepository.save(appointment);

        return convertToDTO(updatedAppointment);
    }
    }

