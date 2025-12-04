// src/main/java/com/example/service/PaymentService.java
package com.example.service;

import com.example.dto.PaymentRequest;
import com.example.dto.PaymentResponse;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
public interface PaymentService {
  
    PaymentResponse create(PaymentRequest req);

    // RUTA: GET /payments/patient/{id}
    List<PaymentResponse> getPaymentsByPatient(Long patientId);

    // RUTA: GET /payments/consultation/{id}
    PaymentResponse getPaymentByConsultation(Long consultationId);

    // RUTA: PUT /payments/{id}/status
    PaymentResponse changeStatus(Long paymentId, String newStatus);

    // RUTA: GET /payments/statistics
    Map<String, BigDecimal> getPaymentStatistics();
}