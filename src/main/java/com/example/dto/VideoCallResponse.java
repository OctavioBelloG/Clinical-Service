package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VideoCallResponse {

    @JsonProperty("videocall_id")
    Long videocallId;

    @JsonProperty("appointment_id")
    Long appointmentId;

    @JsonProperty("patient_name")
    String patientName;

    @JsonProperty("doctor_name")
    String doctorName;

    @JsonProperty("link")
    String link;

    @JsonProperty("provider")
    String provider;

    @JsonProperty("status")
    String status;
}