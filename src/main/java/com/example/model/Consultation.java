package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CONSULTATIONS")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_consultation_id")
    private Long consultationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_appointment_id", nullable = false)
    private Appointment appointment;

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis;

    @Column(name = "treatment", columnDefinition = "TEXT")
    private String treatment;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "consultation_date")
    private LocalDateTime consultationDate;

    // Relaciones pendientes (comentadas por ahora como solicitaste)
    /*
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalEvidence> medicalEvidences;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SatisfactionSurvey satisfactionSurvey;
    */
}