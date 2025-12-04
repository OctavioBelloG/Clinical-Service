// src/main/java/com/example/repository/InvoiceRepository.java
package com.example.repository;

import com.example.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    // RUTA: GET /invoices/payment/{id}
    Invoice findByPayment_PaymentId(Long paymentId);
    
    boolean existsByInvoiceNumber(String invoiceNumber);
    boolean existsByPayment_PaymentId(Long paymentId);
}