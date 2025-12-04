package com.example.controller;

import com.example.dto.ScheduleRequest;
import com.example.dto.ScheduleResponse;
import com.example.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping
    public List<ScheduleResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ScheduleResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> create(@RequestBody ScheduleRequest req) {
        ScheduleResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/schedules/" + created.getScheduleId())).body(created);
    }

    @PutMapping("/{id}")
    public ScheduleResponse update(@PathVariable Long id, @RequestBody ScheduleRequest req) {
        return service.update(id, req);
    }
}
