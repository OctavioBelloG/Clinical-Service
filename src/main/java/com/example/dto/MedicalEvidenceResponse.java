package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Builder
public class MedicalEvidenceResponse {

    @JsonProperty("evidence_id")
    Long evidenceId;

    @JsonProperty("consultation_id")
    Long consultationId;

    @JsonProperty("file_type")
    String fileType;

    @JsonProperty("file_url")
    String fileUrl;

    @JsonProperty("description")
    String description;

    @JsonProperty("upload_date")
    LocalDateTime uploadDate;
}