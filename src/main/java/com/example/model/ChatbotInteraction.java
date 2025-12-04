package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "CHATBOT_INTERACTIONS")
public class ChatbotInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_chatbot_id")
    private Long chatbotId;

    // --- CAMBIO MICROSERVICIOS ---
    @Column(name = "fk_patient_id", nullable = false)
    private Long patientId;
    // -----------------------------

    @Column(name = "reported_symptoms", columnDefinition = "TEXT")
    private String reportedSymptoms;

    @Column(name = "recommendation", columnDefinition = "TEXT")
    private String recommendation;

    @Column(name = "interaction_date")
    private LocalDateTime interactionDate;

    @OneToMany(mappedBy = "chatbotInteraction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalValidation> medicalValidations;
}