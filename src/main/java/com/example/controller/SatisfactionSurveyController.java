// src/main/java/com/example/controller/SatisfactionSurveyController.java (Fragmento con rutas clave)
package com.example.controller;

import com.example.dto.SatisfactionSurveyRequest;
import com.example.dto.SatisfactionSurveyResponse;
import com.example.service.SatisfactionSurveyService;
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
@RequestMapping("/api/v1/surveys")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Tag(name = "Satisfaction Surveys", description = "Operations for collecting and analyzing patient satisfaction")
public class SatisfactionSurveyController {

    private final SatisfactionSurveyService service;

    // RUTA: POST /surveys
    @Operation(summary = "Registrar nueva encuesta de satisfacción")
    @PostMapping
    public ResponseEntity<SatisfactionSurveyResponse> create(@Valid @RequestBody SatisfactionSurveyRequest req) {
        SatisfactionSurveyResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/surveys/" + created.getSurveyId())).body(created);
    }

    // RUTA: GET /surveys/consultation/{id}
    @Operation(summary = "Ver encuesta por ID de consulta")
    @GetMapping("/consultation/{id}")
    public SatisfactionSurveyResponse getSurveyByConsultation(@PathVariable Long id) {
        return service.getSurveyByConsultation(id);
    }

    // RUTA: GET /surveys/doctor/{id}
    @Operation(summary = "Ver encuestas asociadas a un doctor")
    @GetMapping("/doctor/{id}")
    public List<SatisfactionSurveyResponse> getSurveysByDoctor(@PathVariable Long id) {
        return service.getSurveysByDoctor(id);
    }

    // RUTA: GET /surveys/statistics
    @Operation(summary = "Obtener estadísticas de calificación promedio por doctor")
    @GetMapping("/statistics")
    public Map<String, Double> getRatingStatistics() {
        return service.getRatingStatistics();
    }
}