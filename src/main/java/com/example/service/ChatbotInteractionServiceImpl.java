package com.example.service;

import com.example.dto.ChatbotInteractionRequest;
import com.example.dto.ChatbotInteractionResponse;
import com.example.mapper.ChatbotInteractionMapper;
import com.example.model.ChatbotInteraction;
import com.example.repository.ChatbotInteractionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ChatbotInteractionServiceImpl implements ChatbotInteractionService {

    private final ChatbotInteractionRepository repository;
// Dentro de ChatbotInteractionServiceImpl.java

    @Override
    public List<ChatbotInteractionResponse> findByPatientId(Long patientId, int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        
        // CORRECCIÓN: Quitar el guion bajo y el segundo "Patient"
        Page<ChatbotInteraction> interactions = repository.findByPatientId(patientId, pageReq);

        return interactions.getContent().stream()
                .map(ChatbotInteractionMapper::toResponse)
                .toList();
    }

    @Override
    public ChatbotInteractionResponse findById(Long chatbotId) {
        ChatbotInteraction interaction = repository.findById(chatbotId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Interacción no encontrada con ID: " + chatbotId));
        return ChatbotInteractionMapper.toResponse(interaction);
    }

    @Override
    public ChatbotInteractionResponse create(ChatbotInteractionRequest req) {
        ChatbotInteraction interactionToSave = ChatbotInteractionMapper.toEntity(req);
        ChatbotInteraction saved = repository.save(interactionToSave);
        return ChatbotInteractionMapper.toResponse(saved);
    }

    @Override
    public ChatbotInteractionResponse update(Long chatbotId, ChatbotInteractionRequest req) {
        ChatbotInteraction existing = repository.findById(chatbotId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Interacción no encontrada con ID: " + chatbotId));

        ChatbotInteractionMapper.copyToEntity(req, existing);
        ChatbotInteraction saved = repository.save(existing);
        return ChatbotInteractionMapper.toResponse(saved);
    }
    
}