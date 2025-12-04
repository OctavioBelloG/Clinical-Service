// src/main/java/com/example/dto/InvoiceRequest.java
package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class InvoiceRequest {
    @NotNull(message = "El ID de pago es obligatorio")
    private Long paymentId;

    @NotBlank(message = "El número de factura es obligatorio")
    private String invoiceNumber;

    private Timestamp issueDate;
}