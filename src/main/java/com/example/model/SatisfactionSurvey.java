package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "SATISFACTION_SURVEYS")
public class SatisfactionSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_survey_id")
    private Long surveyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_consultation_id", nullable = false, unique = true)
    private Consultation consultation;

    // --- CAMBIO MICROSERVICIOS ---
    @Column(name = "fk_patient_id", nullable = false)
    private Long patientId; 
    // -----------------------------

    @Column(name = "rating", nullable = false)
    private Integer rating; 

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "survey_date")
    private Timestamp surveyDate;
}