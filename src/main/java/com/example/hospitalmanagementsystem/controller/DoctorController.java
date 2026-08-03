package com.example.hospitalmanagementsystem.controller;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.hospitalmanagementsystem.dto.DoctorDTO;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public DoctorDTO addDoctor(@RequestBody DoctorDTO dto)
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
    public DoctorDTO updateDoctor(@RequestBody DoctorDTO dto)
    {
        return service.updateDoctor(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id)
    {
        return service.deleteDoctor(id);
    }
}
