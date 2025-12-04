package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicalEvidenceRequest {

    @NotNull(message = "El ID de la consulta es obligatorio")
    private Long consultationId;

    @NotBlank(message = "El tipo de archivo es obligatorio")
    @Pattern(regexp = "^(image|document|video|audio)$",
            message = "El tipo de archivo debe ser: image, document, video o audio")
    private String fileType;

    @NotBlank(message = "La URL del archivo es obligatoria")
    private String fileUrl;

    private String description;
}