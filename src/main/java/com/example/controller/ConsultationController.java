package com.example.controller;

import com.example.dto.ConsultationRequest;
import com.example.dto.ConsultationResponse;
import com.example.service.ConsultationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Consultations", description = "Provides methods for managing medical consultations")
public class ConsultationController {

    private final ConsultationService service;

    /**
     * Obtiene todas las consultas (sin paginación)
     * GET /consultations
     */
    @Operation(summary = "Get all consultations")
    @GetMapping
    public ResponseEntity<List<ConsultationResponse>> findAll() {
        List<ConsultationResponse> consultations = service.findAll();
        return new ResponseEntity<>(consultations, HttpStatus.OK); // 200
    }

    /**
     * Obtiene todas las consultas con paginación
     * GET /consultations/pagination?page=0&pageSize=10
     */
    @Operation(summary = "Get all consultations with pagination")
    @GetMapping(value = "/pagination", params = {"page", "pageSize"})
    public ResponseEntity<List<ConsultationResponse>> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<ConsultationResponse> consultations = service.findAllPaginated(page, pageSize);
        return new ResponseEntity<>(consultations, HttpStatus.OK); // 200
    }

    /**
     * Obtiene una consulta por ID
     * GET /consultations/{id}
     */
    @Operation(summary = "Get a consultation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> findById(@PathVariable Long id) {
        ConsultationResponse consultation = service.findById(id);
        return new ResponseEntity<>(consultation, HttpStatus.OK); // 200
    }

    /**
     * Obtiene consultas por ID del paciente con paginación
     * GET /consultations/patient/{id}?page=0&pageSize=10
     */
    @Operation(summary = "Get consultations by patient ID")
    @GetMapping(value = "/patient/{id}", params = {"page", "pageSize"})
    public ResponseEntity<List<ConsultationResponse>> findByPatientId(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<ConsultationResponse> consultations = service.findByPatientId(id, page, pageSize);
        return new ResponseEntity<>(consultations, HttpStatus.OK); // 200
    }

    /**
     * Obtiene consultas por ID del doctor con paginación
     * GET /consultations/doctor/{id}?page=0&pageSize=10
     */
    @Operation(summary = "Get consultations by doctor ID")
    @GetMapping(value = "/doctor/{id}", params = {"page", "pageSize"})
    public ResponseEntity<List<ConsultationResponse>> findByDoctorId(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<ConsultationResponse> consultations = service.findByDoctorId(id, page, pageSize);
        return new ResponseEntity<>(consultations, HttpStatus.OK); // 200
    }

    /**
     * Crea una nueva consulta
     * POST /consultations
     */
    @Operation(summary = "Create a new consultation")
    @PostMapping
    public ResponseEntity<ConsultationResponse> create(@Valid @RequestBody ConsultationRequest req) {
        ConsultationResponse savedConsultation = service.create(req);
        return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED); // 201
    }

    /**
     * Actualiza una consulta existente
     * PUT /consultations/{id}
     */
    @Operation(summary = "Update an existing consultation")
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ConsultationRequest req) {
        ConsultationResponse updatedConsultation = service.update(id, req);
        return new ResponseEntity<>(updatedConsultation, HttpStatus.OK); // 200
    }
}