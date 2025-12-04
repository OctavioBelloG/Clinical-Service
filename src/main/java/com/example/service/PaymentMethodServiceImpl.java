// src/main/java/com/example/service/PaymentMethodServiceImpl.java
package com.example.service;

import com.example.dto.PaymentMethodRequest;
import com.example.dto.PaymentMethodResponse;
import com.example.mapper.PaymentMethodMapper;
import com.example.model.PaymentMethod;
import com.example.repository.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository repository;

    @Override
    public List<PaymentMethodResponse> findAll() {
        return repository.findAll().stream().map(PaymentMethodMapper::toResponse).toList();
    }

    @Override
    public PaymentMethodResponse findById(Long id) {
        PaymentMethod method = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found: " + id));
        return PaymentMethodMapper.toResponse(method);
    }

    @Override
    public PaymentMethodResponse create(PaymentMethodRequest req) {
        if (repository.existsByMethodNameIgnoreCase(req.getMethodName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del método ya existe.");
        }
        PaymentMethod saved = repository.save(PaymentMethodMapper.toEntity(req));
        return PaymentMethodMapper.toResponse(saved);
    }

    @Override
    public PaymentMethodResponse update(Long id, PaymentMethodRequest req) {
        PaymentMethod existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found: " + id));

        // Validación de unicidad si el nombre cambia
        if (!existing.getMethodName().equalsIgnoreCase(req.getMethodName()) && 
            repository.existsByMethodNameIgnoreCase(req.getMethodName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del método ya existe.");
        }

        PaymentMethodMapper.copyToEntity(req, existing);
        PaymentMethod saved = repository.save(existing);
        return PaymentMethodMapper.toResponse(saved);
    }
}