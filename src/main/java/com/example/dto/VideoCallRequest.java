package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VideoCallRequest {

    @NotNull(message = "El ID de la cita es obligatorio")
    private Long appointmentId;

    @NotBlank(message = "El enlace de la videollamada es obligatorio")
    private String link;

    private String provider;

    @Pattern(regexp = "^(active|ended|cancelled)$",
            message = "El estado debe ser: active, ended o cancelled")
    private String status;
}