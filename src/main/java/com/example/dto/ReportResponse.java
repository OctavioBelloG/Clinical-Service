// src/main/java/com/example/dto/ReportResponse.java
package com.example.dto;

import lombok.Builder;
import lombok.Value;
import java.sql.Timestamp;

@Value
@Builder
public class ReportResponse {
    Long reportId;
    String reportType;
    Long generatedByUserId;
    String generatedByUserName;
    Timestamp generatedAt;
    String dataJson; // La data JSONB como String
}