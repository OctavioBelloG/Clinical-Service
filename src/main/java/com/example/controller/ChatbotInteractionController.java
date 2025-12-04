package com.example.controller;

import com.example.dto.ChatbotInteractionRequest;
import com.example.dto.ChatbotInteractionResponse;
import com.example.service.ChatbotInteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chatbot")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Chatbot Interactions", description = "Provides methods for managing chatbot interactions")
public class ChatbotInteractionController {

    private final ChatbotInteractionService service;

    /**
     * Obtener interacciones por paciente
     * GET /chatbot/patient/{id}?page=0&pageSize=10
     */
    @Operation(summary = "Get chatbot interactions by patient ID")
    @GetMapping(value = "/patient/{id}", params = {"page", "pageSize"})
    public ResponseEntity<List<ChatbotInteractionResponse>> findByPatientId(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<ChatbotInteractionResponse> interactions = service.findByPatientId(id, page, pageSize);
        return new ResponseEntity<>(interactions, HttpStatus.OK); // 200
    }

    /**
     * Obtener interacción por ID
     * GET /chatbot/{id}
     */
    @Operation(summary = "Get chatbot interaction by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ChatbotInteractionResponse> findById(@PathVariable Long id) {
        ChatbotInteractionResponse interaction = service.findById(id);
        return new ResponseEntity<>(interaction, HttpStatus.OK); // 200
    }

    /**
     * Crear nueva interacción con chatbot
     * POST /chatbot
     */
    @Operation(summary = "Create a new chatbot interaction")
    @PostMapping
    public ResponseEntity<ChatbotInteractionResponse> create(@Valid @RequestBody ChatbotInteractionRequest req) {
        ChatbotInteractionResponse interaction = service.create(req);
        return new ResponseEntity<>(interaction, HttpStatus.CREATED); // 201
    }

    /**
     * Actualizar interacción
     * PUT /chatbot/{id}
     */
    @Operation(summary = "Update a chatbot interaction")
    @PutMapping("/{id}")
    public ResponseEntity<ChatbotInteractionResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ChatbotInteractionRequest req) {
        ChatbotInteractionResponse interaction = service.update(id, req);
        return new ResponseEntity<>(interaction, HttpStatus.OK); // 200
    }
}