// src/main/java/com/example/dto/SatisfactionSurveyRequest.java
package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class SatisfactionSurveyRequest {
    @NotNull(message = "El ID de la consulta es obligatorio")
    private Long consultationId;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long patientId; 

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación debe ser 1 o mayor")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer rating; 

    private String comment;
    private Timestamp surveyDate;
}