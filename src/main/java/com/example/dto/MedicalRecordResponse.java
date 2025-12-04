package com.example.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicalRecordResponse {
    private Long recordId;
    private Long patientId;
    private String allergies;
    private String vaccines;
    private String medicalHistory;
    private String notes;
    private Timestamp lastUpdated;
}
