package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MEDICAL_EVIDENCE")
public class MedicalEvidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_evidence_id")
    private Long evidenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_consultation_id", nullable = false)
    private Consultation consultation;

    @Column(name = "file_type")
    private String fileType; // 'image', 'document', 'video', 'audio'

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
}