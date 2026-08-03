package com.example.hospitalmanagementsystem.dto;

import com.example.hospitalmanagementsystem.model.Doctor;

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
    public String getName()
    {
    return name;
}

    public void setName(String name)
    {
        this.name = name;
    }
    public String getSpecialization()
    {
        return  specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }

}


