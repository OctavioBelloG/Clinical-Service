package com.example.service;

import com.example.dto.DoctorRequest;
import com.example.dto.DoctorResponse;
import com.example.dto.PatientResponse;
import com.example.mapper.DoctorMapper;
import com.example.model.Doctor;
import com.example.repository.AppointmentRepository;
import com.example.repository.DoctorRepository;
import com.example.repository.SpecialtyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;
    private final SpecialtyRepository specialtyRepository;
    private final AppointmentRepository appointmentRepository;
    
    // CAMBIO: Inyectamos el servicio de comunicación externa en lugar de UserRepository
    private final ExternalUserService externalUserService; 

    @Override
    public DoctorResponse findById(Long doctorId) {
        Doctor doctor = repository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found: " + doctorId));
        return DoctorMapper.toResponse(doctor);
    }

    @Override
    public DoctorResponse create(DoctorRequest req) {
        if (repository.existsByRegistrationNumber(req.getRegistrationNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de registro ya existe.");
        }

        // CAMBIO: Validación vía REST hacia users-service
        if (!externalUserService.existsUserById(req.getUserId())) {
            throw new EntityNotFoundException("User not found via Microservice check with ID: " + req.getUserId());
        }

        if (!specialtyRepository.existsById(req.getSpecialtyId())) {
            throw new EntityNotFoundException("Specialty not found with ID: " + req.getSpecialtyId());
        }

        Doctor saved = repository.save(DoctorMapper.toEntity(req));
        return DoctorMapper.toResponse(saved);
    }

    @Override
    public DoctorResponse update(Long doctorId, DoctorRequest req) {
        Doctor existing = repository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found: " + doctorId));

        if (!existing.getRegistrationNumber().equals(req.getRegistrationNumber()) &&
                repository.existsByRegistrationNumber(req.getRegistrationNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de registro ya está en uso.");
        }

        DoctorMapper.copyToEntity(req, existing);
        Doctor saved = repository.save(existing);
        return DoctorMapper.toResponse(saved);
    }

    @Override
    public List<DoctorResponse> getDoctorsPaged(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        return repository.findAll(pageReq).getContent().stream().map(DoctorMapper::toResponse).toList();
    }

    @Override
    public List<DoctorResponse> getDoctorsBySpecialty(Long specialtyId, int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        return repository.findBySpecialty_SpecialtyId(specialtyId, pageReq)
                .getContent().stream().map(DoctorMapper::toResponse).toList();
    }

    @Override
    public List<DoctorResponse> getTopRatedDoctors(int limit) {
        PageRequest pageReq = PageRequest.of(0, limit);
        return repository.findTopByOrderByRatingDesc(pageReq).stream()
                .map(DoctorMapper::toResponse).toList();
    }

    @Override
    public List<DoctorResponse> getAvailableDoctors(String date, Long specialtyId) {
        return List.of(); // Lógica pendiente
    }

    @Override
    public List<PatientResponse> getDoctorPatients(Long doctorId, int page, int pageSize) {
        // Esta lógica es compleja en microservicios (requiere agregación). 
        // Por ahora retornamos vacío para que compile.
        return List.of();
    }
}