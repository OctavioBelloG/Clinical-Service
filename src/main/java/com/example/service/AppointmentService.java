package com.example.service;

import java.util.List;

import com.example.dto.AppointmentRequest;
import com.example.dto.AppointmentResponse;

public interface AppointmentService {
    List<AppointmentResponse> findAll();
    AppointmentResponse findById(Long id);
    AppointmentResponse create(AppointmentRequest req);
    AppointmentResponse update(Long id, AppointmentRequest req);
}
