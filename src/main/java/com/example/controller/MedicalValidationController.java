package com.example.controller;

import com.example.dto.MedicalValidationRequest;
import com.example.dto.MedicalValidationResponse;
import com.example.service.MedicalValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/validations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Medical Validations", description = "Provides methods for managing medical validations from chatbot")
public class MedicalValidationController {

    private final MedicalValidationService service;

    /**
     * Obtener validaciones por chatbot interaction
     * GET /validations/chatbot/{id}
     */
    @Operation(summary = "Get validations by chatbot interaction ID")
    @GetMapping("/chatbot/{id}")
    public ResponseEntity<List<MedicalValidationResponse>> findByChatbotId(@PathVariable Long id) {
        List<MedicalValidationResponse> validations = service.findByChatbotId(id);
        return new ResponseEntity<>(validations, HttpStatus.OK); // 200
    }

    /**
     * Obtener validaciones por doctor
     * GET /validations/doctor/{id}?page=0&pageSize=10
     */
    @Operation(summary = "Get validations by doctor ID")
    @GetMapping(value = "/doctor/{id}", params = {"page", "pageSize"})
    public ResponseEntity<List<MedicalValidationResponse>> findByDoctorId(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<MedicalValidationResponse> validations = service.findByDoctorId(id, page, pageSize);
        return new ResponseEntity<>(validations, HttpStatus.OK); // 200
    }

    /**
     * Obtener validación por ID
     * GET /validations/{id}
     */
    @Operation(summary = "Get validation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalValidationResponse> findById(@PathVariable Long id) {
        MedicalValidationResponse validation = service.findById(id);
        return new ResponseEntity<>(validation, HttpStatus.OK); // 200
    }

    /**
     * Crear nueva validación médica
     * POST /validations
     */
    @Operation(summary = "Create a new medical validation")
    @PostMapping
    public ResponseEntity<MedicalValidationResponse> create(@Valid @RequestBody MedicalValidationRequest req) {
        MedicalValidationResponse validation = service.create(req);
        return new ResponseEntity<>(validation, HttpStatus.CREATED); // 201
    }

    /**
     * Actualizar validación médica
     * PUT /validations/{id}
     */
    @Operation(summary = "Update a medical validation")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalValidationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody MedicalValidationRequest req) {
        MedicalValidationResponse validation = service.update(id, req);
        return new ResponseEntity<>(validation, HttpStatus.OK); // 200
    }
}