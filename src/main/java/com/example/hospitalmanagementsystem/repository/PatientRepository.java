package com.example.hospitalmanagementsystem.repository;
import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository< Patient , Integer>
{
    List<Patient> findByName(String name);
    List<Patient> findByDisease(String disease);

    List<Patient> findByDoctorName(String doctorName);

    List<Patient> findByAge(int age);
    List<Patient> findByGender(String gender);

    List<Patient> findByDiseaseAndDoctorName(String disease, String doctorName);

    List<Patient> findByAgeGreaterThan(int age);

    List <Patient> findByAgeBetween(int startAge , int endAge);
}
