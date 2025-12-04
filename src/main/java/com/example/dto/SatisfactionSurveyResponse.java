package com.example.dto;

import lombok.Builder;
import lombok.Value;
import java.sql.Timestamp;

@Value
@Builder
public class SatisfactionSurveyResponse {
    Long surveyId;
    
    // FKs
    Long consultationId;
    Long patientId;
    
    Integer rating; 
    String comment;
    Timestamp surveyDate;
}