package com.example.controller;

import com.example.dto.MedicalEvidenceRequest;
import com.example.dto.MedicalEvidenceResponse;
import com.example.service.MedicalEvidenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidence")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Medical Evidence", description = "Provides methods for managing medical evidence files")
public class MedicalEvidenceController {

    private final MedicalEvidenceService service;

    /**
     * Subir evidencia médica (imagen, documento, video)
     * POST /evidence/upload
     */
    @Operation(summary = "Upload medical evidence")
    @PostMapping("/upload")
    public ResponseEntity<MedicalEvidenceResponse> uploadEvidence(@Valid @RequestBody MedicalEvidenceRequest req) {
        MedicalEvidenceResponse evidence = service.uploadEvidence(req);
        return new ResponseEntity<>(evidence, HttpStatus.CREATED); // 201
    }

    /**
     * Ver evidencias asociadas a una consulta
     * GET /evidence/consultation/{id}
     */
    @Operation(summary = "Get evidence by consultation ID")
    @GetMapping("/consultation/{id}")
    public ResponseEntity<List<MedicalEvidenceResponse>> findByConsultationId(@PathVariable Long id) {
        List<MedicalEvidenceResponse> evidences = service.findByConsultationId(id);
        return new ResponseEntity<>(evidences, HttpStatus.OK); // 200
    }

    /**
     * Ver detalle de evidencia
     * GET /evidence/{id}
     */
    @Operation(summary = "Get evidence by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalEvidenceResponse> findById(@PathVariable Long id) {
        MedicalEvidenceResponse evidence = service.findById(id);
        return new ResponseEntity<>(evidence, HttpStatus.OK); // 200
    }
}