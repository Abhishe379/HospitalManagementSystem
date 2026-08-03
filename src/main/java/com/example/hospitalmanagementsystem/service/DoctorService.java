package com.example.hospitalmanagementsystem.service;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospitalmanagementsystem.dto.DoctorDTO;

import java.util.List;
@Service
public class DoctorService {
    @Autowired
    private  DoctorRepository repository;
    public DoctorDTO addDoctor(DoctorDTO dto)
    {
        Doctor doctor = convertToEntity(dto);

        Doctor savedDoctor = repository.save(doctor);

        return convertToDTO(savedDoctor);
    }
    public DoctorDTO getDoctorByIdDTO(int id)
    {
        Doctor doctor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor with ID " + id + " not found"));

        return convertToDTO(doctor);
    }

    public List<DoctorDTO> getAllDoctors()
    {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public Doctor getDoctorById(int id)
    {
        return repository.findById(id).orElse(null);
    }
    public DoctorDTO updateDoctor(DoctorDTO dto)
    {
        Doctor doctor = convertToEntity(dto);

        doctor.setId(dto.getId());

        Doctor updatedDoctor = repository.save(doctor);

        return convertToDTO(updatedDoctor);
    }
    public String deleteDoctor(int id)
    {
        repository.deleteById(id);
        return "Doctor Deleted Successfully";
    }
    public DoctorDTO convertToDTO(Doctor doctor)
    {
        DoctorDTO dto = new DoctorDTO();

        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setExperience(doctor.getExperience());

        return dto;
    }
    public Doctor convertToEntity(DoctorDTO dto)
    {
        Doctor doctor = new Doctor();

        // ID set मत करें (Database generate करेगा)
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getExperience());

        return doctor;
    }
}
