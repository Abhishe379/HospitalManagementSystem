package com.example.hospitalmanagementsystem.model;
import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @NotBlank(message = "Patient name is required")
    private String name;
    @Positive(message = "Age must be greater than 0")
    private int age;

    @NotBlank(message = "Gender is required")
    private String gender;
    @NotBlank(message = "Disease id required")
    private String disease;
    @NotBlank(message = "Doctor name is required")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Doctor name should contain only letters"
    )
    private String doctorName;
    private String reportPath;

    public Patient() {
    }

    public Patient(int id, String name, int age, String gender, String disease, String doctorName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.doctorName = doctorName;
    }
public  int getId()
{
    return id;
}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }
}
