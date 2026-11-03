package com.example.hospitalmanagementsystem.controller;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.hospitalmanagementsystem.dto.DoctorDTO;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public DoctorDTO addDoctor( @Valid @RequestBody DoctorDTO dto)
    {
        return service.addDoctor(dto);
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctors()
    {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable int id)
    {
        return service.getDoctorByIdDTO(id);
    }

    @PutMapping
    public DoctorDTO updateDoctor(@Valid @RequestBody DoctorDTO dto)
    {
        return service.updateDoctor(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id)
    {
        return service.deleteDoctor(id);
    }

    @GetMapping("/page")
    public Page<Doctor> getDoctorsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.getDoctorsWithPaginationAndSorting(
                page,
                size,
                sortBy,
                direction
        );
    }
    @GetMapping("/search")
    public Page<Doctor> searchDoctorsWithPaginationAndSorting(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchDoctorsWithPaginationAndSorting(
                name,
                page,
                size,
                sortBy,
                direction
        );
    }
    @GetMapping("/experience")
    public Page<Doctor> searchDoctorsByExperience(
            @RequestParam int experience,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchDoctorsByExperience(
                experience,
                page,
                size,
                sortBy,
                direction
        );
    }
    @GetMapping("/specialization")
    public Page<Doctor> searchDoctorsBySpecialization(
            @RequestParam String specialization,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.searchDoctorsBySpecialization(
                specialization,
                page,
                size,
                sortBy,
                direction
        );
    }
}
