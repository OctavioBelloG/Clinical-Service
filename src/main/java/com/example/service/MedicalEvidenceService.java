package com.example.service;

import com.example.dto.MedicalEvidenceRequest;
import com.example.dto.MedicalEvidenceResponse;

import java.util.List;

public interface MedicalEvidenceService {

    MedicalEvidenceResponse uploadEvidence(MedicalEvidenceRequest req);

    List<MedicalEvidenceResponse> findByConsultationId(Long consultationId);

    MedicalEvidenceResponse findById(Long evidenceId);
}