package com.example.mapper;

import com.example.model.SatisfactionSurvey;
import com.example.model.Consultation;
import com.example.dto.SatisfactionSurveyRequest;
import com.example.dto.SatisfactionSurveyResponse;

public final class SatisfactionSurveyMapper {

    public static SatisfactionSurveyResponse toResponse(SatisfactionSurvey s) {
        if (s == null) return null;
        return SatisfactionSurveyResponse.builder()
                .surveyId(s.getSurveyId())
                .consultationId(s.getConsultation() != null ? s.getConsultation().getConsultationId() : null)
                // CAMBIO: ID directo
                .patientId(s.getPatientId())
                .rating(s.getRating())
                .comment(s.getComment())
                .surveyDate(s.getSurveyDate())
                .build();
    }

    public static SatisfactionSurvey toEntity(SatisfactionSurveyRequest dto) {
        if (dto == null) return null;
        SatisfactionSurvey s = new SatisfactionSurvey();
        
        if (dto.getConsultationId() != null) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(dto.getConsultationId());
            s.setConsultation(consultation);
        }
        // CAMBIO: ID directo
        if (dto.getPatientId() != null) {
            s.setPatientId(dto.getPatientId());
        }
        
        s.setRating(dto.getRating());
        s.setComment(dto.getComment());
        s.setSurveyDate(dto.getSurveyDate());
        return s;
    }

    public static void copyToEntity(SatisfactionSurveyRequest dto, SatisfactionSurvey entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getConsultationId() != null) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(dto.getConsultationId());
            entity.setConsultation(consultation);
        }
        // CAMBIO: ID directo
        if (dto.getPatientId() != null) {
            entity.setPatientId(dto.getPatientId());
        }
        
        entity.setRating(dto.getRating());
        entity.setComment(dto.getComment());
        entity.setSurveyDate(dto.getSurveyDate());
    }
}