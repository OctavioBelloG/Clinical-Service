package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Specialty;
import com.example.dto.SpecialtyRequest;
import com.example.dto.SpecialtyResponse;
import com.example.mapper.SpecialtyMapper;
import com.example.repository.SpecialtyRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository repository;

    @Override
    public List<SpecialtyResponse> findAll() {
        return repository.findAll().stream().map(SpecialtyMapper::toResponse).toList();
    }

    @Override
    public SpecialtyResponse findById(Long id) {
        Specialty s = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Specialty not found: " + id));
        return SpecialtyMapper.toResponse(s);
    }

    @Override
    public SpecialtyResponse create(SpecialtyRequest req) {
        Specialty saved = repository.save(SpecialtyMapper.toEntity(req));
        return SpecialtyMapper.toResponse(saved);
    }

    @Override
    public SpecialtyResponse update(Long id, SpecialtyRequest req) {
        Specialty existing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Specialty not found: " + id));
        SpecialtyMapper.copyToEntity(req, existing);
        Specialty saved = repository.save(existing);
        return SpecialtyMapper.toResponse(saved);
    }
}
