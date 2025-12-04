// src/main/java/com/example/controller/PaymentController.java (Fragmento con rutas clave)
package com.example.controller;

import com.example.dto.PaymentRequest;
import com.example.dto.PaymentResponse;
import com.example.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Payments", description = "Operations for registering, filtering, and managing payments")
public class PaymentController {

    private final PaymentService service;

    // RUTA: POST /payments
    @Operation(summary = "Registrar un nuevo pago")
    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest req) {
        PaymentResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/payments/" + created.getPaymentId())).body(created);
    }

    // RUTA: GET /payments/patient/{id}
    @Operation(summary = "Ver pagos por ID de paciente")
    @GetMapping("/patient/{id}")
    public List<PaymentResponse> getPaymentsByPatient(@PathVariable Long id) {
        return service.getPaymentsByPatient(id);
    }

    // RUTA: GET /payments/consultation/{id}
    @Operation(summary = "Ver pago por ID de consulta")
    @GetMapping("/consultation/{id}")
    public PaymentResponse getPaymentByConsultation(@PathVariable Long id) {
        return service.getPaymentByConsultation(id);
    }
    
    // RUTA: PUT /payments/{id}/status
    @Operation(summary = "Cambiar el estado del pago")
    @PutMapping("/{id}/status")
    public PaymentResponse changeStatus(
            @PathVariable Long id, 
            @RequestParam("status") String newStatus) {
        return service.changeStatus(id, newStatus);
    }
    
    // RUTA: GET /payments/statistics
    @Operation(summary = "Obtener estadísticas de pagos (ej: totales por método)")
    @GetMapping("/statistics")
    public Map<String, BigDecimal> getPaymentStatistics() {
        return service.getPaymentStatistics();
    }
}