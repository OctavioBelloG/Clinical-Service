package com.example.service;

import com.example.dto.MedicalEvidenceRequest;
import com.example.dto.MedicalEvidenceResponse;
import com.example.mapper.MedicalEvidenceMapper;
import com.example.model.Consultation;
import com.example.model.MedicalEvidence;
import com.example.repository.ConsultationRepository;
import com.example.repository.MedicalEvidenceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MedicalEvidenceServiceImpl implements MedicalEvidenceService {

    private final MedicalEvidenceRepository repository;
    private final ConsultationRepository consultationRepository;

    @Override
    public MedicalEvidenceResponse uploadEvidence(MedicalEvidenceRequest req) {
        // Validar que la consulta existe
        Consultation consultation = consultationRepository.findById(req.getConsultationId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Consulta no encontrada con ID: " + req.getConsultationId()));

        MedicalEvidence evidenceToSave = MedicalEvidenceMapper.toEntity(req);
        evidenceToSave.setConsultation(consultation);

        MedicalEvidence saved = repository.save(evidenceToSave);
        return MedicalEvidenceMapper.toResponse(saved);
    }

    @Override
    public List<MedicalEvidenceResponse> findByConsultationId(Long consultationId) {
        // Validar que la consulta existe
        if (!consultationRepository.existsById(consultationId)) {
            throw new EntityNotFoundException("Consulta no encontrada con ID: " + consultationId);
        }

        return repository.findByConsultation_ConsultationId(consultationId).stream()
                .map(MedicalEvidenceMapper::toResponse)
                .toList();
    }

    @Override
    public MedicalEvidenceResponse findById(Long evidenceId) {
        MedicalEvidence evidence = repository.findById(evidenceId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Evidencia no encontrada con ID: " + evidenceId));

        return MedicalEvidenceMapper.toResponse(evidence);
    }
}