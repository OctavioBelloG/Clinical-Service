package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "MEDICAL_RECORDS")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_record_id")
    private Long recordId;

    // --- CAMBIO MICROSERVICIOS ---
    @Column(name = "fk_patient_id", unique = true) // Es OneToOne lógico
    private Long patientId;
    // -----------------------------

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "vaccines")
    private String vaccines;

    @Column(name = "medical_history")
    private String medicalHistory;

    @Column(name = "notes")
    private String notes;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;
}