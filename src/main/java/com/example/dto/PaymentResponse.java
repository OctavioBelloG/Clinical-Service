// src/main/java/com/example/dto/PaymentResponse.java
package com.example.dto;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

@Value
@Builder
public class PaymentResponse {
    @JsonProperty("id of payment")
    Long paymentId;
    Long patientId;
    Long consultationId;
    Long methodId;
    String methodName; 
    BigDecimal amount;
    String status;
    Timestamp paymentDate;
}