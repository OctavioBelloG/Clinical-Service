package com.example.mapper;

import com.example.model.Payment;
import com.example.model.Consultation;
import com.example.model.PaymentMethod;
import com.example.dto.PaymentRequest;
import com.example.dto.PaymentResponse;

public final class PaymentMapper {

    public static PaymentResponse toResponse(Payment p) {
        if (p == null) return null;
        return PaymentResponse.builder()
                .paymentId(p.getPaymentId())
                // CAMBIO: ID directo
                .patientId(p.getPatientId())
                .consultationId(p.getConsultation() != null ? p.getConsultation().getConsultationId() : null)
                .methodId(p.getMethod() != null ? p.getMethod().getMethodId() : null)
                .methodName(p.getMethod() != null ? p.getMethod().getMethodName() : null)
                .amount(p.getAmount())
                .status(p.getStatus())
                .paymentDate(p.getPaymentDate())
                .build();
    }

    public static Payment toEntity(PaymentRequest dto) {
        if (dto == null) return null;
        Payment p = new Payment();
        
        // CAMBIO: ID directo
        if (dto.getPatientId() != null) {
            p.setPatientId(dto.getPatientId());
        }
        if (dto.getConsultationId() != null) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(dto.getConsultationId());
            p.setConsultation(consultation);
        }
        if (dto.getMethodId() != null) {
            PaymentMethod method = new PaymentMethod();
            method.setMethodId(dto.getMethodId());
            p.setMethod(method);
        }
        
        p.setAmount(dto.getAmount());
        p.setStatus(dto.getStatus());
        p.setPaymentDate(dto.getPaymentDate());
        return p;
    }

    public static void copyToEntity(PaymentRequest dto, Payment entity) {
        if (dto == null || entity == null) return;
        
        // CAMBIO: ID directo
        if (dto.getPatientId() != null) {
            entity.setPatientId(dto.getPatientId());
        }
        if (dto.getConsultationId() != null) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(dto.getConsultationId());
            entity.setConsultation(consultation);
        }
        if (dto.getMethodId() != null) {
            PaymentMethod method = new PaymentMethod();
            method.setMethodId(dto.getMethodId());
            entity.setMethod(method);
        }
        
        entity.setAmount(dto.getAmount());
        entity.setStatus(dto.getStatus());
        entity.setPaymentDate(dto.getPaymentDate());
    }
}