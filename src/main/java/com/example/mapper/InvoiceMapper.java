package com.example.mapper;

import com.example.model.Invoice;
import com.example.model.Payment;
import com.example.dto.InvoiceRequest;
import com.example.dto.InvoiceResponse;

public final class InvoiceMapper {

    public static InvoiceResponse toResponse(Invoice i) {
        if (i == null) return null;
        return InvoiceResponse.builder()
                .invoiceId(i.getInvoiceId())
                .paymentId(i.getPayment() != null ? i.getPayment().getPaymentId() : null)
                .invoiceNumber(i.getInvoiceNumber())
                .issueDate(i.getIssueDate())
                .build();
    }

    public static Invoice toEntity(InvoiceRequest dto) {
        if (dto == null) return null;
        Invoice i = new Invoice();
        
        if (dto.getPaymentId() != null) {
            Payment p = new Payment();
            p.setPaymentId(dto.getPaymentId());
            i.setPayment(p);
        }
        
        i.setInvoiceNumber(dto.getInvoiceNumber());
        i.setIssueDate(dto.getIssueDate());
        return i;
    }

    public static void copyToEntity(InvoiceRequest dto, Invoice entity) {
        if (dto == null || entity == null) return;
        
        // En este caso, el paymentId es la FK y no debería cambiarse fácilmente, 
        // pero se incluye para consistencia si se usa en un PUT.
        if (dto.getPaymentId() != null) {
            Payment p = new Payment();
            p.setPaymentId(dto.getPaymentId());
            entity.setPayment(p);
        }
        
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setIssueDate(dto.getIssueDate());
    }
}