package com.example.controller;

import com.example.dto.VideoCallRequest;
import com.example.dto.VideoCallResponse;
import com.example.service.VideoCallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/videocalls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Video Calls", description = "Provides methods for managing video consultations")
public class VideoCallController {

    private final VideoCallService service;

    /**
     * Generar enlace de videollamada
     * POST /videocalls
     */
    @Operation(summary = "Create a new video call")
    @PostMapping
    public ResponseEntity<VideoCallResponse> create(@Valid @RequestBody VideoCallRequest req) {
        VideoCallResponse videoCall = service.create(req);
        return new ResponseEntity<>(videoCall, HttpStatus.CREATED); // 201
    }

    /**
     * Consultar videollamada de cita
     * GET /videocalls/appointment/{id}
     */
    @Operation(summary = "Get video call by appointment ID")
    @GetMapping("/appointment/{id}")
    public ResponseEntity<VideoCallResponse> findByAppointmentId(@PathVariable Long id) {
        VideoCallResponse videoCall = service.findByAppointmentId(id);
        return new ResponseEntity<>(videoCall, HttpStatus.OK); // 200
    }

    /**
     * Cambiar estado (activa, finalizada, cancelada)
     * PUT /videocalls/{id}/status?status=ended
     */
    @Operation(summary = "Update video call status")
    @PutMapping("/{id}/status")
    public ResponseEntity<VideoCallResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        VideoCallResponse videoCall = service.updateStatus(id, status);
        return new ResponseEntity<>(videoCall, HttpStatus.OK); // 200
    }
}