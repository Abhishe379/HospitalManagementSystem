package com.example.hospitalmanagementsystem.service;
import com.example.hospitalmanagementsystem.dto.AppointmentDTO;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.AppointmentRepository;
import com.example.hospitalmanagementsystem.repository.PatientRepository;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

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
        Appointment appointment = convertToEntity(dto);
        appointment.setId(dto.getId());
        Appointment updateAppointment = appointmentRepository.save(appointment);
        return convertToDTO(updateAppointment);
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
    }

