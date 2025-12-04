package com.example.service;

import com.example.dto.MedicalValidationRequest;
import com.example.dto.MedicalValidationResponse;

import java.util.List;

public interface MedicalValidationService {

    List<MedicalValidationResponse> findByChatbotId(Long chatbotId);

    List<MedicalValidationResponse> findByDoctorId(Long doctorId, int page, int pageSize);

    MedicalValidationResponse findById(Long validationId);

    MedicalValidationResponse create(MedicalValidationRequest req);

    MedicalValidationResponse update(Long validationId, MedicalValidationRequest req);
}