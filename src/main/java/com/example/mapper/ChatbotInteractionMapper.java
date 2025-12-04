package com.example.mapper;

import com.example.dto.ChatbotInteractionRequest;
import com.example.dto.ChatbotInteractionResponse;
import com.example.model.ChatbotInteraction;
// import com.example.model.Patient; <-- BORRADO

import java.time.LocalDateTime;

public final class ChatbotInteractionMapper {

    private ChatbotInteractionMapper() {}

    public static ChatbotInteractionResponse toResponse(ChatbotInteraction interaction) {
        if (interaction == null) return null;

        return ChatbotInteractionResponse.builder()
                .chatbotId(interaction.getChatbotId())
                // CORRECCIÓN: Usamos el ID directo
                .patientId(interaction.getPatientId())
                .patientName(null) // No podemos saber el nombre aquí
                .reportedSymptoms(interaction.getReportedSymptoms())
                .recommendation(interaction.getRecommendation())
                .interactionDate(interaction.getInteractionDate())
                .build();
    }

    public static ChatbotInteraction toEntity(ChatbotInteractionRequest dto) {
        if (dto == null) return null;

        ChatbotInteraction interaction = new ChatbotInteraction();

        // CORRECCIÓN: Asignamos ID directo
        if (dto.getPatientId() != null) {
            interaction.setPatientId(dto.getPatientId());
        }

        interaction.setReportedSymptoms(dto.getReportedSymptoms());
        interaction.setRecommendation(dto.getRecommendation());
        interaction.setInteractionDate(LocalDateTime.now());

        return interaction;
    }

    public static void copyToEntity(ChatbotInteractionRequest dto, ChatbotInteraction entity) {
        if (dto == null || entity == null) return;

        // CORRECCIÓN: Asignamos ID directo
        if (dto.getPatientId() != null) {
            entity.setPatientId(dto.getPatientId());
        }

        entity.setReportedSymptoms(dto.getReportedSymptoms());
        entity.setRecommendation(dto.getRecommendation());
    }
}