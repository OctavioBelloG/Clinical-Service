package com.example.service;

import com.example.dto.SatisfactionSurveyRequest;
import com.example.dto.SatisfactionSurveyResponse;
import com.example.mapper.SatisfactionSurveyMapper;
import com.example.model.SatisfactionSurvey;
import com.example.repository.SatisfactionSurveyRepository;
import com.example.repository.ConsultationRepository;
import com.example.repository.DoctorRepository; 
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SatisfactionSurveyServiceImpl implements SatisfactionSurveyService {

    private final SatisfactionSurveyRepository repository;
    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final ExternalUserService externalUserService;

    @Override
    public SatisfactionSurveyResponse create(SatisfactionSurveyRequest req) {
        if (!consultationRepository.existsById(req.getConsultationId())) {
            throw new EntityNotFoundException("Consultation not found.");
        }
        
        if (!externalUserService.existsPatientById(req.getPatientId())) {
            throw new EntityNotFoundException("Patient not found (External check).");
        }

        if (repository.existsByConsultation_ConsultationId(req.getConsultationId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una encuesta para la consulta ID: " + req.getConsultationId());
        }

        SatisfactionSurvey saved = repository.save(SatisfactionSurveyMapper.toEntity(req));
        return SatisfactionSurveyMapper.toResponse(saved);
    }
    
    @Override
    public SatisfactionSurveyResponse getSurveyByConsultation(Long consultationId) {
        SatisfactionSurvey survey = repository.findByConsultation_ConsultationId(consultationId);
        if (survey == null) {
            throw new EntityNotFoundException("Satisfaction Survey not found for Consultation ID: " + consultationId);
        }
        return SatisfactionSurveyMapper.toResponse(survey);
    }
    
    @Override
    public List<SatisfactionSurveyResponse> getSurveysByDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
             throw new EntityNotFoundException("Doctor not found.");
        }
        
        // NOTA: Asegúrate de que SatisfactionSurveyRepository tenga un @Query para esto
        // ya que la entidad no tiene doctorId directo.
        return repository.findByConsultation_Appointment_Doctor_DoctorId(doctorId).stream()
                .map(SatisfactionSurveyMapper::toResponse)
                .toList();
    }

    @Override
    public Map<String, Double> getRatingStatistics() {
        return repository.getAverageRatingByDoctor().stream()
                .collect(Collectors.toMap(
                    obj -> (String) obj[0],
                    obj -> ((Number) obj[1]).doubleValue()
                ));
    }
}