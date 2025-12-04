package com.example.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorRequest {
   @NotNull(message = "El ID de usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El ID de especialidad es obligatorio")
    private Long specialtyId;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 255, message = "El nombre no debe exceder los 255 caracteres")
    private String fullName;

    @NotBlank(message = "El número de registro es obligatorio")
    @Size(max = 255, message = "El número de registro no debe exceder los 255 caracteres")
    private String registrationNumber;

    @DecimalMin(value = "0.00", message = "La calificación mínima es 0.00")
    @DecimalMax(value = "5.00", message = "La calificación máxima es 5.00")
    private BigDecimal rating;
}
