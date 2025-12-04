package com.example.service;

import com.example.dto.MedicalValidationRequest;
import com.example.dto.MedicalValidationResponse;
import com.example.mapper.MedicalValidationMapper;
import com.example.model.ChatbotInteraction;
import com.example.model.Doctor;
import com.example.model.MedicalValidation;
import com.example.repository.ChatbotInteractionRepository;
import com.example.repository.DoctorRepository;
import com.example.repository.MedicalValidationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MedicalValidationServiceImpl implements MedicalValidationService {

    private final MedicalValidationRepository repository;
    private final ChatbotInteractionRepository chatbotRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public List<MedicalValidationResponse> findByChatbotId(Long chatbotId) {
        if (!chatbotRepository.existsById(chatbotId)) {
            throw new EntityNotFoundException("Interacción de chatbot no encontrada con ID: " + chatbotId);
        }

        return repository.findByChatbotInteraction_ChatbotId(chatbotId).stream()
                .map(validation -> {
                    Hibernate.initialize(validation.getDoctor());
                    Hibernate.initialize(validation.getChatbotInteraction());
                    // CAMBIO: Quitamos initialize de patient, porque ya no hay objeto paciente dentro de chatbot
                    return MedicalValidationMapper.toResponse(validation);
                })
                .toList();
    }

    @Override
    public List<MedicalValidationResponse> findByDoctorId(Long doctorId, int page, int pageSize) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new EntityNotFoundException("Doctor no encontrado con ID: " + doctorId);
        }

        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<MedicalValidation> validations = repository.findByDoctor_DoctorId(doctorId, pageReq);

        return validations.getContent().stream()
                .map(validation -> {
                    Hibernate.initialize(validation.getDoctor());
                    Hibernate.initialize(validation.getChatbotInteraction());
                    return MedicalValidationMapper.toResponse(validation);
                })
                .toList();
    }

    @Override
    public MedicalValidationResponse findById(Long validationId) {
        MedicalValidation validation = repository.findById(validationId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Validación médica no encontrada con ID: " + validationId));

        Hibernate.initialize(validation.getDoctor());
        Hibernate.initialize(validation.getChatbotInteraction());
        return MedicalValidationMapper.toResponse(validation);
    }

    @Override
    public MedicalValidationResponse create(MedicalValidationRequest req) {
        ChatbotInteraction chatbot = chatbotRepository.findById(req.getChatbotId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Interacción de chatbot no encontrada con ID: " + req.getChatbotId()));

        Doctor doctor = doctorRepository.findById(req.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Doctor no encontrado con ID: " + req.getDoctorId()));

        MedicalValidation validationToSave = MedicalValidationMapper.toEntity(req);
        validationToSave.setChatbotInteraction(chatbot);
        validationToSave.setDoctor(doctor);

        MedicalValidation saved = repository.save(validationToSave);

        Hibernate.initialize(saved.getDoctor());
        Hibernate.initialize(saved.getChatbotInteraction());
        return MedicalValidationMapper.toResponse(saved);
    }

    @Override
    public MedicalValidationResponse update(Long validationId, MedicalValidationRequest req) {
        MedicalValidation existing = repository.findById(validationId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Validación médica no encontrada con ID: " + validationId));

        if (!existing.getChatbotInteraction().getChatbotId().equals(req.getChatbotId())) {
            ChatbotInteraction newChatbot = chatbotRepository.findById(req.getChatbotId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Interacción de chatbot no encontrada con ID: " + req.getChatbotId()));
            existing.setChatbotInteraction(newChatbot);
        }

        if (!existing.getDoctor().getDoctorId().equals(req.getDoctorId())) {
            Doctor newDoctor = doctorRepository.findById(req.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Doctor no encontrado con ID: " + req.getDoctorId()));
            existing.setDoctor(newDoctor);
        }

        MedicalValidationMapper.copyToEntity(req, existing);
        MedicalValidation saved = repository.save(existing);

        Hibernate.initialize(saved.getDoctor());
        Hibernate.initialize(saved.getChatbotInteraction());
        return MedicalValidationMapper.toResponse(saved);
    }
}