package com.example.hospitalmanagementsystem.controller;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import com.example.hospitalmanagementsystem.dto.PatientDTO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService service;
    @PostMapping
    public PatientDTO addPatient(@Valid @RequestBody PatientDTO dto)
    {
        return service.addPatient(dto);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients()
    {
        return service.getAllPatients();
    }
    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable int id)
    {
        return service.getPatientByIdDTO(id);
    }
    @PutMapping
    public PatientDTO updatePatient( @Valid @RequestBody PatientDTO dto)
    {
        return  service.updatePatientDTO(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id)
    {
        String message = service.deletePatient(id);

        return ResponseEntity.ok(message);
    }


    @GetMapping("/search/{name}")
    public  List<Patient> searchPatientByName(@PathVariable String name)
    {
        return service.searchPatientByName(name);
    }
    @GetMapping("/search")
    public Page<Patient> searchPatientsWithPaginationAndSorting(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction)
    {
        return service.searchPatientsWithPaginationAndSorting(
                name, page, size, sortBy, direction);
    }
    @GetMapping("/sort/name")
    public List<Patient> sortPatientsByName()

    {
        return service.sortPatientsByName();
    }
    @GetMapping("/page")
    public Page<Patient> getPatientsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.getPatientsWithPaginationAndSorting(
                page, size, sortBy, direction
        );
    }
@GetMapping("/disease/{disease}")
    public List<Patient> searchPatientByDisease(@PathVariable String disease)
{
    return service.searchPatientByDisease(disease);
}
@GetMapping("/doctor/{doctorName}")
    public List<Patient> searchPatientByDoctorName(@PathVariable String doctorName)
{
    return service.searchPatientByDoctorName(doctorName);
}
@GetMapping("/age/{age}")
    public List<Patient> searchPatientByAge(@PathVariable int age)
{
    return service.searchPatientByAge(age);
}
@GetMapping("/gender/{gender}")
    public List<Patient> searchPatientsByGender(@PathVariable String gender )
{
    return service.searchPatientByGender(gender);
}
@GetMapping("/search/{disease}/{doctorName}")
    public List<Patient> searchPatientsByDiseaseAndDoctorName(@PathVariable String disease , @PathVariable String doctorName)
{
    return service.searchPatientByDiseaseAndDoctorName(disease, doctorName);
}
@GetMapping("/ageGreaterThan/{age}")
    public  List<Patient> searchPatientsByGreaterThan(@PathVariable int age)
{
    return service.searchPatientAgeGreaterThan(age);
}
@GetMapping("/ageBetween/{startAge}/{endAge}")
    public List<Patient> getPatientsAgeBetween(@PathVariable int startAge , @PathVariable int endAge)
{
    return service.searchPatientByAgeBetween(startAge , endAge);
}@PostMapping(
            value = "/{id}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadReport(
            @PathVariable int id,
            @RequestParam("file") MultipartFile file) {

        return service.uploadReport(id, file);
    }

@GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadReport(@PathVariable int id)
{
    return  service.downloadReport(id);
}

@GetMapping("/advanced-search")
    public Page<Patient> advancedSearchPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String disease,
            @RequestParam(required = false) String gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue= "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
)
{
    return service.searchPatients(
            name,
            disease,
            gender,
            page,
            size,
            sortBy,
            direction
    );
}


}
