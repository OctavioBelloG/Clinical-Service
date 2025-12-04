package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Schedule;
import com.example.dto.ScheduleRequest;
import com.example.dto.ScheduleResponse;
import com.example.mapper.ScheduleMapper;
import com.example.repository.ScheduleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    @Override
    public List<ScheduleResponse> findAll() {
        return repository.findAll().stream().map(ScheduleMapper::toResponse).toList();
    }

    @Override
    public ScheduleResponse findById(Long id) {
        Schedule s = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule not found: " + id));
        return ScheduleMapper.toResponse(s);
    }

    @Override
    public ScheduleResponse create(ScheduleRequest req) {
        Schedule saved = repository.save(ScheduleMapper.toEntity(req));
        return ScheduleMapper.toResponse(saved);
    }

    @Override
    public ScheduleResponse update(Long id, ScheduleRequest req) {
        Schedule existing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule not found: " + id));
        ScheduleMapper.copyToEntity(req, existing);
        Schedule saved = repository.save(existing);
        return ScheduleMapper.toResponse(saved);
    }
}
