package com.example.service;

import com.example.dto.ConsultationRequest;
import com.example.dto.ConsultationResponse;
import com.example.mapper.ConsultationMapper;
import com.example.model.Appointment;
import com.example.model.Consultation;
import com.example.repository.AppointmentRepository;
import com.example.repository.ConsultationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository repository;
    private final AppointmentRepository appointmentRepository;

    // 1. Implementación de findAll
    @Override
    public List<ConsultationResponse> findAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 2. Implementación de findAllPaginated
    @Override
    public List<ConsultationResponse> findAllPaginated(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        return repository.findAll(pageReq).getContent().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 3. Implementación de findById
    @Override
    public ConsultationResponse findById(Long consultationId) {
        Consultation consultation = repository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con ID: " + consultationId));
        return mapToResponse(consultation);
    }

    // 4. Implementación de findByPatientId
    @Override
    public List<ConsultationResponse> findByPatientId(Long patientId, int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        // Usamos el método corregido del repositorio (Navegación: Consulta -> Cita -> ID Paciente)
        Page<Consultation> consultations = repository.findByAppointment_PatientId(patientId, pageReq);
        
        return consultations.getContent().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 5. Implementación de findByDoctorId
    @Override
    public List<ConsultationResponse> findByDoctorId(Long doctorId, int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        // Usamos el método del repositorio (Navegación: Consulta -> Cita -> Doctor -> ID)
        Page<Consultation> consultations = repository.findByAppointment_Doctor_DoctorId(doctorId, pageReq);
        
        return consultations.getContent().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 6. Implementación de create
    @Override
    public ConsultationResponse create(ConsultationRequest req) {
        // Validar que la cita existe
        Appointment appointment = appointmentRepository.findById(req.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con ID: " + req.getAppointmentId()));

        // Validar unicidad (1 consulta por cita)
        if (repository.existsByAppointment_AppointmentId(req.getAppointmentId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una consulta para esta cita");
        }

        Consultation consultationToSave = ConsultationMapper.toEntity(req);
        consultationToSave.setAppointment(appointment);

        Consultation saved = repository.save(consultationToSave);
        return mapToResponse(saved);
    }

    // 7. Implementación de update
    @Override
    public ConsultationResponse update(Long consultationId, ConsultationRequest req) {
        Consultation existing = repository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con ID: " + consultationId));

        // Validar si cambió la cita asociada
        if (!existing.getAppointment().getAppointmentId().equals(req.getAppointmentId())) {
            Appointment newAppointment = appointmentRepository.findById(req.getAppointmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con ID: " + req.getAppointmentId()));
            
            // Si cambia de cita, verificar que la nueva no tenga ya consulta
            if (repository.existsByAppointment_AppointmentId(req.getAppointmentId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cita seleccionada ya tiene consulta");
            }
            existing.setAppointment(newAppointment);
        }

        ConsultationMapper.copyToEntity(req, existing);
        Consultation saved = repository.save(existing);
        return mapToResponse(saved);
    }

    // Método auxiliar para evitar repetir código de inicialización
    private ConsultationResponse mapToResponse(Consultation consultation) {
        if (consultation.getAppointment() != null) {
            Hibernate.initialize(consultation.getAppointment());
            // Nota: Ya no inicializamos Patient ni Doctor aquí porque en Appointment son solo IDs (Long)
        }
        return ConsultationMapper.toResponse(consultation);
    }
}