package com.example.hospitalmanagementsystem.service;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.exception.PatientNotfoundException;
import com.example.hospitalmanagementsystem.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.hospitalmanagementsystem.dto.PatientDTO;

import java.util.List;

@Service
public class PatientService {
    public PatientDTO convertToDTO(Patient patient) {

        PatientDTO dto = new PatientDTO();

        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setGender(patient.getGender());
        dto.setDisease(patient.getDisease());
        dto.setDoctorName(patient.getDoctorName());

        return dto;
    }


    // DTO to Entity Conversion
    public Patient convertToEntity(PatientDTO dto) {

        Patient patient = new Patient();

       // patient.setId(dto.getId());
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setDisease(dto.getDisease());
        patient.setDoctorName(dto.getDoctorName());

        return patient;
    }

    public PatientDTO getPatientByIdDTO(int id)
    {
        Patient patient = repository.findById(id) .orElseThrow(() -> new PatientNotfoundException("Patient with ID " + id + "not found"));
        return convertToDTO(patient);
    }

    @Autowired
    private PatientRepository repository;



    private int id;
    public Patient getPatientById(int id)
    {
        return  repository.findById(id)
                .orElseThrow(()->
                        new PatientNotfoundException("Patient with ID "+ id + "not found"));
    }
    public Patient updatePatient(Patient patient)
    {
        return  repository.save(patient);
    }
public String deletePatient(int id)
{
    Patient patient = repository.findById(id) .orElseThrow(()-> new PatientNotfoundException("Patient with ID" + id + "not found"));
    repository.deleteById(id);
    return "Patient Deleted Successfully";
}

public List<Patient> searchPatientByName(String name)
{
    return repository.findByName(name);
}

public List<Patient> sortPatientsByName()
{
    return repository.findAll(Sort.by(Sort.Direction.ASC,"name"));
}
public Page<Patient> getPatientsWithPagination(int page , int size)
{
    Pageable pageable = PageRequest.of(page, size);
    return  repository.findAll(pageable);
}
public List<Patient> searchPatientByDisease(String disease)
{
    return repository.findByDisease(disease);
}
public List<Patient> searchPatientByDoctorName(String doctorName)
{
    return repository.findByDoctorName(doctorName);
}
public List<Patient> searchPatientByAge(int age)
{
    return repository.findByAge(age);
}
public List<Patient> searchPatientByGender(String gender)
{
    return  repository.findByGender(gender);
}
public List<Patient> searchPatientByDiseaseAndDoctorName(String disease , String doctorName)
{
    return repository.findByDiseaseAndDoctorName(disease, doctorName);
}
public List<Patient> searchPatientAgeGreaterThan(int age){
        return repository.findByAgeGreaterThan(age);
}
public List<Patient> searchPatientByAgeBetween(int startAge , int endAge)
{
    return repository.findByAgeBetween(startAge , endAge);
}
    public PatientDTO addPatient(PatientDTO dto)
    {
        Patient patient = convertToEntity(dto);

        Patient savedPatient = repository.save(patient);

        return convertToDTO(savedPatient);
    }
    public List<PatientDTO> getAllPatients()
    {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public PatientDTO updatePatientDTO(PatientDTO dto)
    {
        Patient patient = convertToEntity(dto);
        Patient updatedPatient = repository.save(patient);
        return convertToDTO(updatedPatient);
    }
}
