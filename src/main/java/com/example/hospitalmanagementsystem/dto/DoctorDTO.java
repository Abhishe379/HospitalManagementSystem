package com.example.hospitalmanagementsystem.dto;

import com.example.hospitalmanagementsystem.model.Doctor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DoctorDTO
{
    private Integer id;
    private String name;
    private String specialization;
    private int experience;

    public DoctorDTO()
    {

    }

    public DoctorDTO(Integer id , String name , String specialization , int experience) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }
    public int getId() {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    @NotBlank(message = "Doctor name cannot be empty")
    public String getName()
    {
    return name;
}

    public void setName(String name)
    {
        this.name = name;
    }
    @NotBlank(message = "Specialization cannot be empty")
    public String getSpecialization()
    {
        return  specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    @Positive(message = "Experience must be greater than 0")
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }

}


