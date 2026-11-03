package com.example.hospitalmanagementsystem.service;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.exception.PatientNotfoundException;
import com.example.hospitalmanagementsystem.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.hospitalmanagementsystem.dto.PatientDTO;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        dto.setReportPath(patient.getReportPath());

        return dto;
    }


    // DTO to Entity Conversion
    public Patient convertToEntity(PatientDTO dto) {

        Patient patient = new Patient();

        patient.setId(dto.getId());
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setDisease(dto.getDisease());
        patient.setDoctorName(dto.getDoctorName());
        patient.setReportPath(dto.getReportPath());
        return patient;
    }

    public PatientDTO getPatientByIdDTO(int id)
    {
        Patient patient = repository.findById(id) .orElseThrow(() -> new PatientNotfoundException("Patient with ID " + id + "not found"));
        return convertToDTO(patient);
    }

    @Autowired
    private PatientRepository repository;



   // private int id;
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
    public Page<Patient> getPatientsWithPagination(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
    public Page<Patient> getPatientsWithPaginationAndSorting(
            int page,
            int size,
            String sortBy,
            String direction)
    {
        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable);
    }
    public Page<Patient> searchPatientsWithPaginationAndSorting(
            String name,
            int page,
            int size,
            String sortBy,
            String direction)
    {
        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findByNameContainingIgnoreCase(name, pageable);
    }
    public Page<Patient> searchPatients(
            String name,
            String disease,
            String gender,
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

        return repository.searchPatients(
                name,
                disease,
                gender,
                pageable
        );
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
        Patient existingPatient = repository.findById(dto.getId())
                .orElseThrow(() ->
                        new PatientNotfoundException(
                                "Patient with ID " + dto.getId() + " not found"));

        existingPatient.setName(dto.getName());
        existingPatient.setAge(dto.getAge());
        existingPatient.setGender(dto.getGender());
        existingPatient.setDisease(dto.getDisease());
        existingPatient.setDoctorName(dto.getDoctorName());

        Patient updatedPatient = repository.save(existingPatient);

        return convertToDTO(updatedPatient);
    }

    public  String uploadReport(int id  ,  MultipartFile file)
    {
        Patient patient = repository.findById(id) .orElseThrow(()->new PatientNotfoundException("Patient Not found"));
        try
        {
            String fileName = System.currentTimeMillis() +" _ " + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");

            if(!Files.exists(uploadPath))
            {
                Files.createDirectories(uploadPath);
            }
            Files.copy(file.getInputStream() , uploadPath.resolve(fileName) , StandardCopyOption.REPLACE_EXISTING);

            patient.setReportPath(fileName);

            repository.save(patient);

            return "Report Uploaded Successfully";
        } catch (IOException e) {
            throw new RuntimeException("File Upload Failed");
        }

    }
    public ResponseEntity<Resource> downloadReport(int id)
    {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        try
        {
            Path filePath = Paths.get("uploads")
                    .resolve(patient.getReportPath())
                    .normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists())
            {
                throw new RuntimeException("Report not found");
            }

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\""
                    )
                    .body(resource);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to download report");
        }
    }


}
