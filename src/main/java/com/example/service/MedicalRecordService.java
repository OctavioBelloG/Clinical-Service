package com.example.service;

import java.util.List;

import com.example.dto.MedicalRecordRequest;
import com.example.dto.MedicalRecordResponse;

public interface MedicalRecordService {
  // RUTA: GET /records/{patientId}
    MedicalRecordResponse findByPatientId(Long patientId); 

    // RUTA: POST /records (Crear expediente)
    MedicalRecordResponse create(MedicalRecordRequest req);

    // RUTA: PUT /records/{id}
    MedicalRecordResponse update(Long id, MedicalRecordRequest req);

    // RUTA: PUT /records/{id}/updateDate
    MedicalRecordResponse updateLastUpdatedDate(Long id); // No necesita Request DTO

    // RUTA: GET /records/search?keyword=
    List<MedicalRecordResponse> searchRecords(String keyword);
}
