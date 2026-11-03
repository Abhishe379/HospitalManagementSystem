package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor , Integer>
{
    List<Doctor> findByName(String name);

    List<Doctor> findByExperience(int experience);

    Page<Doctor> findByExperience(
            int experience,
            Pageable pageable
    );

    List<Doctor> findBySpecialization(String specialization);

    Page<Doctor> findByNameContainingIgnoreCase(String name,
                                                Pageable pageable);

    Page<Doctor> findBySpecializationContainingIgnoreCase(String specialization, Pageable pageable);

}
