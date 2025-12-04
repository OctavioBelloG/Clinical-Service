// src/main/java/com/example/dto/PaymentRequest.java
package com.example.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class PaymentRequest {
    @NotNull(message = "El ID del paciente es obligatorio")
    private Long patientId;
    
    @NotNull(message = "El ID de la consulta es obligatorio")
    private Long consultationId;

    @NotNull(message = "El ID del método de pago es obligatorio")
    private Long methodId;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser positivo")
    private BigDecimal amount;

    @NotBlank(message = "El estado es obligatorio")
    private String status; // pending, paid, cancelled

    private Timestamp paymentDate;
}