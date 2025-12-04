package com.example.service;

import java.util.List;

import com.example.dto.SpecialtyRequest;
import com.example.dto.SpecialtyResponse;

public interface SpecialtyService {
    List<SpecialtyResponse> findAll();
    SpecialtyResponse findById(Long id);
    SpecialtyResponse create(SpecialtyRequest req);
    SpecialtyResponse update(Long id, SpecialtyRequest req);
}
