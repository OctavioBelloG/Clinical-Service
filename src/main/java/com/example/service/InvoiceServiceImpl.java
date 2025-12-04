// src/main/java/com/example/service/InvoiceServiceImpl.java (Fragmento con rutas clave)
package com.example.service;

import com.example.dto.InvoiceRequest;
import com.example.dto.InvoiceResponse;
import com.example.mapper.InvoiceMapper;
import com.example.model.Invoice;
import com.example.repository.InvoiceRepository;
import com.example.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repository;
    private final PaymentRepository paymentRepository;

    @Override
    public InvoiceResponse create(InvoiceRequest req) {
        // 1. Validar FK
        if (!paymentRepository.existsById(req.getPaymentId())) throw new EntityNotFoundException("Payment not found.");

        // 2. Validar unicidad de número de factura
        if (repository.existsByInvoiceNumber(req.getInvoiceNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de factura ya existe.");
        }
        
        // 3. Validar unicidad: Un solo invoice por pago
        if (repository.existsByPayment_PaymentId(req.getPaymentId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una factura para el pago ID: " + req.getPaymentId());
        }

        Invoice saved = repository.save(InvoiceMapper.toEntity(req));
        return InvoiceMapper.toResponse(saved);
    }
    
    @Override
    public InvoiceResponse getInvoiceByPaymentId(Long paymentId) {
        Invoice invoice = repository.findByPayment_PaymentId(paymentId);
        if (invoice == null) {
            throw new EntityNotFoundException("Invoice not found for Payment ID: " + paymentId);
        }
        return InvoiceMapper.toResponse(invoice);
    }
    
    @Override
    public InvoiceResponse findById(Long id) {
        Invoice invoice = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found: " + id));
        return InvoiceMapper.toResponse(invoice);
    }
}