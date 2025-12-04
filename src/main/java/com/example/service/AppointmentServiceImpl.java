package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Appointment;
import com.example.dto.AppointmentRequest;
import com.example.dto.AppointmentResponse;
import com.example.mapper.AppointmentMapper;
import com.example.repository.AppointmentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    @Override
    public List<AppointmentResponse> findAll() {
        return repository.findAll().stream().map(AppointmentMapper::toResponse).toList();
    }

    @Override
    public AppointmentResponse findById(Long id) {
        Appointment a = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + id));
        return AppointmentMapper.toResponse(a);
    }

    @Override
    public AppointmentResponse create(AppointmentRequest req) {
        Appointment saved = repository.save(AppointmentMapper.toEntity(req));
        return AppointmentMapper.toResponse(saved);
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest req) {
        Appointment existing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + id));
        AppointmentMapper.copyToEntity(req, existing);
        Appointment saved = repository.save(existing);
        return AppointmentMapper.toResponse(saved);
    }
}
