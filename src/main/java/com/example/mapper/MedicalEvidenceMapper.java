package com.example.mapper;

import com.example.dto.MedicalEvidenceRequest;
import com.example.dto.MedicalEvidenceResponse;
import com.example.model.Consultation;
import com.example.model.MedicalEvidence;

import java.time.LocalDateTime;

public final class MedicalEvidenceMapper {

    private MedicalEvidenceMapper() {}

    public static MedicalEvidenceResponse toResponse(MedicalEvidence evidence) {
        if (evidence == null) return null;

        return MedicalEvidenceResponse.builder()
                .evidenceId(evidence.getEvidenceId())
                .consultationId(evidence.getConsultation() != null ?
                        evidence.getConsultation().getConsultationId() : null)
                .fileType(evidence.getFileType())
                .fileUrl(evidence.getFileUrl())
                .description(evidence.getDescription())
                .uploadDate(evidence.getUploadDate())
                .build();
    }

    public static MedicalEvidence toEntity(MedicalEvidenceRequest dto) {
        if (dto == null) return null;

        MedicalEvidence evidence = new MedicalEvidence();

        if (dto.getConsultationId() != null) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(dto.getConsultationId());
            evidence.setConsultation(consultation);
        }

        evidence.setFileType(dto.getFileType());
        evidence.setFileUrl(dto.getFileUrl());
        evidence.setDescription(dto.getDescription());
        evidence.setUploadDate(LocalDateTime.now());

        return evidence;
    }

    public static void copyToEntity(MedicalEvidenceRequest dto, MedicalEvidence entity) {
        if (dto == null || entity == null) return;

        if (dto.getConsultationId() != null) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(dto.getConsultationId());
            entity.setConsultation(consultation);
        }

        entity.setFileType(dto.getFileType());
        entity.setFileUrl(dto.getFileUrl());
        entity.setDescription(dto.getDescription());
    }
}