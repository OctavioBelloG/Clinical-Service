package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "REPORTS")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_report_id")
    private Long reportId;

    @Column(name = "report_type", nullable = false)
    private String reportType; 

    // --- CAMBIO MICROSERVICIOS ---
    @Column(name = "generated_by") 
    private Long generatedByUserId;
    // -----------------------------

    @Column(name = "generated_at")
    private Timestamp generatedAt;

    @Column(name = "data", columnDefinition = "JSONB")
    private String data; 
}