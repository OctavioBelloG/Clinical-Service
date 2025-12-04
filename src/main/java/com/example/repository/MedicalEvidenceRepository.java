package com.example.repository;

import com.example.model.MedicalEvidence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalEvidenceRepository extends JpaRepository<MedicalEvidence, Long> {

    // Buscar evidencias por consultation ID
    List<MedicalEvidence> findByConsultation_ConsultationId(Long consultationId);

    // Buscar evidencias por consultation ID con paginación
    Page<MedicalEvidence> findByConsultation_ConsultationId(Long consultationId, Pageable pageable);
}