package com.example.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MedicalRecordRequest {
    private Long patientId;
    private String allergies;
    private String vaccines;
    private String medicalHistory;
    private String notes;
    private Timestamp lastUpdated;
}
