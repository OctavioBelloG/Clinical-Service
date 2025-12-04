package com.example.service;

import com.example.dto.ConsultationRequest;
import com.example.dto.ConsultationResponse;

import java.util.List;

public interface ConsultationService {

    List<ConsultationResponse> findAll();

    List<ConsultationResponse> findAllPaginated(int page, int pageSize);

    ConsultationResponse findById(Long consultationId);

    List<ConsultationResponse> findByPatientId(Long patientId, int page, int pageSize);

    List<ConsultationResponse> findByDoctorId(Long doctorId, int page, int pageSize);

    ConsultationResponse create(ConsultationRequest req);

    ConsultationResponse update(Long consultationId, ConsultationRequest req);
}