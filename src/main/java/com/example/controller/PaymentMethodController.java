// src/main/java/com/example/controller/PaymentMethodController.java
package com.example.controller;

import com.example.dto.PaymentMethodRequest;
import com.example.dto.PaymentMethodResponse;
import com.example.service.PaymentMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/methods")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Payment Methods", description = "CRUD operations for payment methods")
public class PaymentMethodController {

    private final PaymentMethodService service;

    // RUTA: GET /methods
    @Operation(summary = "Listar todos los métodos de pago")
    @GetMapping
    public List<PaymentMethodResponse> findAll() {
        return service.findAll();
    }

    // RUTA: GET /methods/{id}
    @Operation(summary = "Ver método por ID")
    @GetMapping("/{id}")
    public PaymentMethodResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // RUTA: POST /methods
    @Operation(summary = "Crear nuevo método de pago")
    @PostMapping
    public ResponseEntity<PaymentMethodResponse> create(@Valid @RequestBody PaymentMethodRequest req) {
        PaymentMethodResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/methods/" + created.getMethodId())).body(created);
    }

    // RUTA: PUT /methods/{id}
    @Operation(summary = "Actualizar método de pago")
    @PutMapping("/{id}")
    public PaymentMethodResponse update(@PathVariable Long id, @Valid @RequestBody PaymentMethodRequest req) {
        return service.update(id, req);
    }
}