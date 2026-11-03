package com.example.hospitalmanagementsystem.repository;
import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    Page<Patient> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    @Query("""
       SELECT p FROM Patient p
       WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
       AND (:disease IS NULL OR LOWER(p.disease) = LOWER(:disease))
       AND (:gender IS NULL OR LOWER(p.gender) = LOWER(:gender))
       """)
    Page<Patient> searchPatients(
            @Param("name") String name,
            @Param("disease") String disease,
            @Param("gender") String gender,
            Pageable pageable
    );
}
