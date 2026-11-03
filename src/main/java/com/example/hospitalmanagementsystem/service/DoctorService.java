package com.example.hospitalmanagementsystem.service;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospitalmanagementsystem.dto.DoctorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.example.hospitalmanagementsystem.exception.DoctorNotFoundException;
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
                        new DoctorNotFoundException("Doctor with ID " + id + " not found"));

        return convertToDTO(doctor);
    }

    public List<DoctorDTO> getAllDoctors()
    {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Page<Doctor> getDoctorsWithPaginationAndSorting
            (
                    int page,
                    int size,
                    String sortBy,
                    String direction
            )
    {
        Sort sort;

        if(direction.equalsIgnoreCase("desc"))
        {
            sort = Sort.by(sortBy).descending();

        }
        else {
         sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(page , size , sort);
        return repository.findAll(pageable);
    }

    public Page<Doctor> searchDoctorsWithPaginationAndSorting(
            String name,
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

        return repository.findByNameContainingIgnoreCase(
                name,
                pageable
        );
    }
    public Page<Doctor> searchDoctorsBySpecialization(
            String specialization,
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

        return repository.findBySpecializationContainingIgnoreCase(
                specialization,
                pageable
        );
    }
    public Doctor getDoctorById(int id)
    {
        return repository.findById(id)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor with ID " + id + " not found"));
    }
    public DoctorDTO updateDoctor(DoctorDTO dto)
    {
        Doctor existingDoctor = repository.findById(dto.getId())
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor with ID " + dto.getId() + " not found"));

        existingDoctor.setName(dto.getName());
        existingDoctor.setSpecialization(dto.getSpecialization());
        existingDoctor.setExperience(dto.getExperience());

        Doctor updatedDoctor = repository.save(existingDoctor);

        return convertToDTO(updatedDoctor);
    }
    public String deleteDoctor(int id)
    {
        Doctor doctor = repository.findById(id)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor with ID " + id + " not found"));

        repository.delete(doctor);

        return "Doctor Deleted Successfully";
    }
    public Page<Doctor> searchDoctorsByExperience(
            int experience,
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

        return repository.findByExperience(
                experience,
                pageable
        );
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
