package com.example.service;

import java.util.List;

import com.example.dto.ScheduleRequest;
import com.example.dto.ScheduleResponse;

public interface ScheduleService {
    List<ScheduleResponse> findAll();
    ScheduleResponse findById(Long id);
    ScheduleResponse create(ScheduleRequest req);
    ScheduleResponse update(Long id, ScheduleRequest req);
}
