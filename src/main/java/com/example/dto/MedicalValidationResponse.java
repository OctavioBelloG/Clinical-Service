package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Builder
public class MedicalValidationResponse {

    @JsonProperty("validation_id")
    Long validationId;

    @JsonProperty("chatbot_id")
    Long chatbotId;

    @JsonProperty("doctor_id")
    Long doctorId;

    @JsonProperty("doctor_name")
    String doctorName;

    @JsonProperty("patient_name")
    String patientName;

    @JsonProperty("final_diagnosis")
    String finalDiagnosis;

    @JsonProperty("comments")
    String comments;

    @JsonProperty("validation_date")
    LocalDateTime validationDate;
}