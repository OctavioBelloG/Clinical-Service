package com.example.controller;

import com.example.dto.DoctorRequest;
import com.example.dto.DoctorResponse;
import com.example.dto.PatientResponse;
import com.example.service.DoctorService;
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
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Doctors", description = "Provides methods for managing doctor profiles and searches")
public class DoctorController {

    private final DoctorService service;
    
    // RUTA: GET /doctors/pagination?page=&size=
    @Operation(summary = "Listado paginado de médicos")
    @GetMapping(value = "/pagination", params = {"page", "size"})
    public List<DoctorResponse> getDoctorsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getDoctorsPaged(page, size);
    }
    
    // RUTA: GET /doctors/{id}
    @Operation(summary = "Ver médico por ID")
    @GetMapping("/{id}")
    public DoctorResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    // RUTA: POST /doctors
    @Operation(summary = "Registrar nuevo médico")
    @PostMapping
    public ResponseEntity<DoctorResponse> create(@Valid @RequestBody DoctorRequest req) {
        DoctorResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/doctors/" + created.getDoctorId())).body(created);
    }
    
    // RUTA: PUT /doctors/{id}
    @Operation(summary = "Actualizar médico")
    @PutMapping("/{id}")
    public DoctorResponse update(@PathVariable Long id, @Valid @RequestBody DoctorRequest req) {
        return service.update(id, req);
    }
    
    // RUTA: GET /doctors/specialty/{id}?page=&size=
    @Operation(summary = "Médicos por especialidad (paginado)")
    @GetMapping(value = "/specialty/{id}", params = {"page", "size"})
    public List<DoctorResponse> getDoctorsBySpecialty(
            @PathVariable("id") Long specialtyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getDoctorsBySpecialty(specialtyId, page, size);
    }
    
    // RUTA: GET /doctors/top-rated?limit=10
    @Operation(summary = "Top médicos mejor calificados")
    @GetMapping(value = "/top-rated")
    public List<DoctorResponse> getTopRatedDoctors(
            @RequestParam(defaultValue = "10") int limit) {
        return service.getTopRatedDoctors(limit);
    }
    
    // RUTA: GET /doctors/available?date=&specialty=
    @Operation(summary = "Disponibilidad de médicos por fecha y especialidad")
    @GetMapping(value = "/available")
    public List<DoctorResponse> getAvailableDoctors(
            @RequestParam String date,
            @RequestParam Long specialty) {
        return service.getAvailableDoctors(date, specialty);
    }

    // RUTA: GET /doctors/{id}/patients?page=&size=
    @Operation(summary = "Pacientes atendidos por el médico (paginado)")
    @GetMapping(value = "/{id}/patients", params = {"page", "size"})
    public List<PatientResponse> getDoctorPatients(
            @PathVariable("id") Long doctorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getDoctorPatients(doctorId, page, size);
    }
}