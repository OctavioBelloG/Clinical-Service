// src/main/java/com/example/controller/InvoiceController.java (Fragmento con rutas clave)
package com.example.controller;

import com.example.dto.InvoiceRequest;
import com.example.dto.InvoiceResponse;
import com.example.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Tag(name = "Invoices", description = "Operations for generating and viewing invoices")
public class InvoiceController {

    private final InvoiceService service;

    // RUTA: POST /invoices
    @Operation(summary = "Crear/Generar nueva factura")
    @PostMapping
    public ResponseEntity<InvoiceResponse> create(@Valid @RequestBody InvoiceRequest req) {
        InvoiceResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/invoices/" + created.getInvoiceId())).body(created);
    }

    // RUTA: GET /invoices/{id}
    @Operation(summary = "Ver factura por ID")
    @GetMapping("/{id}")
    public InvoiceResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // RUTA: GET /invoices/payment/{id}
    @Operation(summary = "Ver factura por ID de pago asociado")
    @GetMapping("/payment/{id}")
    public InvoiceResponse getInvoiceByPaymentId(@PathVariable Long id) {
        return service.getInvoiceByPaymentId(id);
    }
}