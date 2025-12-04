package com.example.repository;

import com.example.model.MedicalValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalValidationRepository extends JpaRepository<MedicalValidation, Long> {

    // Buscar validaciones por chatbot interaction ID
    List<MedicalValidation> findByChatbotInteraction_ChatbotId(Long chatbotId);

    // Buscar validaciones por doctor ID
    Page<MedicalValidation> findByDoctor_DoctorId(Long doctorId, Pageable pageable);
}