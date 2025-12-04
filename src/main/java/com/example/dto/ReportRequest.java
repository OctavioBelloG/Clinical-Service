// src/main/java/com/example/dto/ReportRequest.java
package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportRequest {
    @NotBlank(message = "El tipo de reporte es obligatorio")
    private String reportType; // usage, trends, satisfaction, finance
    
    @NotNull(message = "El ID del generador es obligatorio")
    private Long generatedByUserId;
    
    // Filtros de ejemplo para la generación
    private String filterDateStart;
    private String filterDateEnd;
}