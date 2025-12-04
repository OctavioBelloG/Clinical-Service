package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ConsultationResponse {

    @JsonProperty("consultation_id")
    Long consultationId;

    @JsonProperty("appointment_id")
    Long appointmentId;

    @JsonProperty("patient_name")
    String patientName;

    @JsonProperty("doctor_name")
    String doctorName;

    @JsonProperty("diagnosis")
    String diagnosis;

    @JsonProperty("treatment")
    String treatment;

    @JsonProperty("observations")
    String observations;

    @JsonProperty("consultation_date")
    LocalDateTime consultationDate;
}