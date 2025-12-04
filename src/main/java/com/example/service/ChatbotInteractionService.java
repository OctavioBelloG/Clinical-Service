package com.example.service;

import com.example.dto.ChatbotInteractionRequest;
import com.example.dto.ChatbotInteractionResponse;

import java.util.List;

public interface ChatbotInteractionService {

    List<ChatbotInteractionResponse> findByPatientId(Long patientId, int page, int pageSize);

    ChatbotInteractionResponse findById(Long chatbotId);

    ChatbotInteractionResponse create(ChatbotInteractionRequest req);

    ChatbotInteractionResponse update(Long chatbotId, ChatbotInteractionRequest req);
}