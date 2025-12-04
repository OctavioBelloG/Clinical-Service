// src/main/java/com/example/service/PaymentMethodService.java
package com.example.service;

import com.example.dto.PaymentMethodRequest;
import com.example.dto.PaymentMethodResponse;
import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodResponse> findAll();
    PaymentMethodResponse findById(Long id);
    PaymentMethodResponse create(PaymentMethodRequest req);
    PaymentMethodResponse update(Long id, PaymentMethodRequest req);
}