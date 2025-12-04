package com.example.repository;

import com.example.model.ChatbotInteraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatbotInteractionRepository extends JpaRepository<ChatbotInteraction, Long> {
    
    // ANTES: findByPatient_PatientId (Buscaba objeto Patient -> campo patientId)
    // AHORA: findByPatientId (Busca directo el campo Long patientId)
    
    Page<ChatbotInteraction> findByPatientId(Long patientId, Pageable pageable);
}