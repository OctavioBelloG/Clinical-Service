package com.example.controller;

import com.example.dto.SpecialtyRequest;
import com.example.dto.SpecialtyResponse;
import com.example.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/specialties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class SpecialtyController {

    private final SpecialtyService service;

    @GetMapping
    public List<SpecialtyResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SpecialtyResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<SpecialtyResponse> create(@RequestBody SpecialtyRequest req) {
        SpecialtyResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/specialties/" + created.getSpecialtyId())).body(created);
    }

    @PutMapping("/{id}")
    public SpecialtyResponse update(@PathVariable Long id, @RequestBody SpecialtyRequest req) {
        return service.update(id, req);
    }
}
