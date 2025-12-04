package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MEDICAL_VALIDATIONS")
public class MedicalValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_validation_id")
    private Long validationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_chatbot_id", nullable = false)
    private ChatbotInteraction chatbotInteraction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "final_diagnosis", columnDefinition = "TEXT")
    private String finalDiagnosis;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;
}