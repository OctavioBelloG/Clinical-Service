// src/main/java/com/example/service/InvoiceService.java
package com.example.service;

import com.example.dto.InvoiceRequest;
import com.example.dto.InvoiceResponse;

public interface InvoiceService {
    InvoiceResponse findById(Long id);
    InvoiceResponse create(InvoiceRequest req);
    
    // RUTA: GET /invoices/payment/{id}
    InvoiceResponse getInvoiceByPaymentId(Long paymentId);
}