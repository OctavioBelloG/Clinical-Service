// src/main/java/com/example/controller/ReportController.java (Fragmento con rutas clave)
package com.example.controller;

import com.example.dto.ReportRequest;
import com.example.dto.ReportResponse;
import com.example.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Tag(name = "Reports", description = "Operations for generating, managing, and viewing reports")
public class ReportController {

    private final ReportService service;

    // RUTA: POST /reports/generate
    @Operation(summary = "Generar y guardar un nuevo reporte")
    @PostMapping("/generate")
    public ResponseEntity<ReportResponse> generateReport(@Valid @RequestBody ReportRequest req) {
        ReportResponse created = service.generateReport(req);
        return ResponseEntity.created(URI.create("/api/v1/reports/" + created.getReportId())).body(created);
    }
    
    // RUTA: GET /reports/{id}
    @Operation(summary = "Ver reporte por ID")
    @GetMapping("/{id}")
    public ReportResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // RUTA: GET /reports/type/{type}
    @Operation(summary = "Ver reportes filtrados por tipo")
    @GetMapping("/type/{type}")
    public List<ReportResponse> getReportsByType(@PathVariable String type) {
        return service.getReportsByType(type);
    }

    // RUTA: GET /reports/statistics
    @Operation(summary = "Obtener estadísticas: Conteo de reportes por tipo")
    @GetMapping("/statistics")
    public Map<String, Long> getReportCountStatistics() {
        return service.getReportCountStatistics();
    }
}