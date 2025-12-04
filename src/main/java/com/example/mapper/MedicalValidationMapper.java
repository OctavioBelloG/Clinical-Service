package com.example.mapper;

import com.example.dto.MedicalValidationRequest;
import com.example.dto.MedicalValidationResponse;
import com.example.model.ChatbotInteraction;
import com.example.model.Doctor;
import com.example.model.MedicalValidation;

import java.time.LocalDateTime;

public final class MedicalValidationMapper {

    private MedicalValidationMapper() {}

    public static MedicalValidationResponse toResponse(MedicalValidation validation) {
        if (validation == null) return null;

        return MedicalValidationResponse.builder()
                .validationId(validation.getValidationId())
                .chatbotId(validation.getChatbotInteraction() != null ?
                        validation.getChatbotInteraction().getChatbotId() : null)
                .doctorId(validation.getDoctor() != null ? validation.getDoctor().getDoctorId() : null)
                .doctorName(validation.getDoctor() != null ? validation.getDoctor().getFullName() : null)
                
                // CAMBIO: No podemos obtener el nombre del paciente aquí porque no tenemos la tabla Patients.
                // Esto se debe resolver en el Service llamando al users-service.
                .patientName(null) 
                
                .finalDiagnosis(validation.getFinalDiagnosis())
                .comments(validation.getComments())
                .validationDate(validation.getValidationDate())
                .build();
    }

    public static MedicalValidation toEntity(MedicalValidationRequest dto) {
        if (dto == null) return null;

        MedicalValidation validation = new MedicalValidation();

        if (dto.getChatbotId() != null) {
            ChatbotInteraction chatbot = new ChatbotInteraction();
            chatbot.setChatbotId(dto.getChatbotId());
            validation.setChatbotInteraction(chatbot);
        }

        if (dto.getDoctorId() != null) {
            Doctor doctor = new Doctor();
            doctor.setDoctorId(dto.getDoctorId());
            validation.setDoctor(doctor);
        }

        validation.setFinalDiagnosis(dto.getFinalDiagnosis());
        validation.setComments(dto.getComments());
        validation.setValidationDate(LocalDateTime.now());

        return validation;
    }

    public static void copyToEntity(MedicalValidationRequest dto, MedicalValidation entity) {
        if (dto == null || entity == null) return;

        if (dto.getChatbotId() != null) {
            ChatbotInteraction chatbot = new ChatbotInteraction();
            chatbot.setChatbotId(dto.getChatbotId());
            entity.setChatbotInteraction(chatbot);
        }

        if (dto.getDoctorId() != null) {
            Doctor doctor = new Doctor();
            doctor.setDoctorId(dto.getDoctorId());
            entity.setDoctor(doctor);
        }

        entity.setFinalDiagnosis(dto.getFinalDiagnosis());
        entity.setComments(dto.getComments());
    }
}