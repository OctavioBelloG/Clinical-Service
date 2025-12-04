package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicalValidationRequest {

    @NotNull(message = "El ID de la interacción del chatbot es obligatorio")
    private Long chatbotId;

    @NotNull(message = "El ID del doctor es obligatorio")
    private Long doctorId;

    @NotBlank(message = "El diagnóstico final es obligatorio")
    private String finalDiagnosis;

    private String comments;
}