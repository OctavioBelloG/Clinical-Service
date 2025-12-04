package com.example.repository;

import com.example.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    
    // Cambio: de findByPatient_PatientId a findByPatientId
    Optional<MedicalRecord> findByPatientId(Long patientId);
}