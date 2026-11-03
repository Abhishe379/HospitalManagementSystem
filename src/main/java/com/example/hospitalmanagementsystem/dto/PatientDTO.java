package com.example.hospitalmanagementsystem.dto;

public class PatientDTO
{

    private Integer id;
    private String name;
    private int age;
    private String gender;
    private String disease;
    private String doctorName;
    private String reportPath;

    public PatientDTO() {
    }
    public PatientDTO(Integer id, String name, int age, String gender, String disease, String doctorName)
    {
    //public PatientDTO(int id, String name, int age, String gender, String disease, String doctorName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.doctorName = doctorName;
    }

    public int getId() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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