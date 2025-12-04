// src/main/java/com/example/dto/PaymentMethodRequest.java
package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentMethodRequest {
    @NotBlank(message = "El nombre del método es obligatorio")
    @Size(max = 50, message = "El nombre no debe exceder los 50 caracteres")
    private String methodName;

    private String description;
}