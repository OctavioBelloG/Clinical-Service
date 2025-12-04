package com.example.controller;

import com.example.dto.MedicalRecordRequest;
import com.example.dto.MedicalRecordResponse;
import com.example.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.Operation; // Importar
import io.swagger.v3.oas.annotations.tags.Tag; // Importar
import lombok.RequiredArgsConstructor;
// Importar
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Medical Records", description = "Provides methods for managing patient medical records")
public class MedicalRecordController {

    private final MedicalRecordService service;

    // RUTA: GET /records/{patientId} (Ver expediente)
    @Operation(summary = "Ver expediente por ID de paciente")
    @GetMapping("/{patientId}")
    public MedicalRecordResponse getByPatientId(@PathVariable Long patientId) {
        return service.findByPatientId(patientId);
    }

    // RUTA: POST /records
    @Operation(summary = "Crear nuevo expediente médico")
    @PostMapping
    public ResponseEntity<MedicalRecordResponse> create(@RequestBody MedicalRecordRequest req) {
        MedicalRecordResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/records/" + created.getRecordId())).body(created);
    }

    // RUTA: PUT /records/{id}
    @Operation(summary = "Actualizar expediente médico completo")
    @PutMapping("/{id}")
    public MedicalRecordResponse update(@PathVariable Long id, @RequestBody MedicalRecordRequest req) {
        return service.update(id, req);
    }
    
    // RUTA: PUT /records/{id}/updateDate
    @Operation(summary = "Actualizar únicamente la fecha de última modificación")
    @PutMapping("/{id}/updateDate")
    public MedicalRecordResponse updateLastUpdatedDate(@PathVariable Long id) {
        return service.updateLastUpdatedDate(id);
    }

    // RUTA: GET /records/search?keyword=
    @Operation(summary = "Búsqueda de expedientes por palabra clave")
    @GetMapping(value = "/search", params = "keyword")
    public List<MedicalRecordResponse> searchRecords(@RequestParam String keyword) {
        return service.searchRecords(keyword);
    }
}