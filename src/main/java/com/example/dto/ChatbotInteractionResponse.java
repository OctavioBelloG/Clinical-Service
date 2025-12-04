package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Builder
public class ChatbotInteractionResponse {

    @JsonProperty("chatbot_id")
    Long chatbotId;

    @JsonProperty("patient_id")
    Long patientId;

    @JsonProperty("patient_name")
    String patientName;

    @JsonProperty("reported_symptoms")
    String reportedSymptoms;

    @JsonProperty("recommendation")
    String recommendation;

    @JsonProperty("interaction_date")
    LocalDateTime interactionDate;
}