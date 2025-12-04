package com.example.service;

import java.util.List;

import com.example.dto.DoctorRequest;
import com.example.dto.DoctorResponse;
import com.example.dto.PatientResponse;

public interface DoctorService {
  List<DoctorResponse> getDoctorsPaged(int page, int pageSize); 

    // RUTA: GET /doctors/{id}
    DoctorResponse findById(Long doctorId);

    // RUTA: POST /doctors
    DoctorResponse create(DoctorRequest req);

    // RUTA: PUT /doctors/{id}
    DoctorResponse update(Long doctorId, DoctorRequest req);
    
    // RUTA: GET /doctors/specialty/{id}
    List<DoctorResponse> getDoctorsBySpecialty(Long specialtyId, int page, int pageSize); 

    // RUTA: GET /doctors/top-rated?limit=10
    List<DoctorResponse> getTopRatedDoctors(int limit);

    // RUTA: GET /doctors/available?date=&specialty=
    List<DoctorResponse> getAvailableDoctors(String date, Long specialtyId);
    
    // RUTA: GET /doctors/{id}/patients?page=&size=
    List<PatientResponse> getDoctorPatients(Long doctorId, int page, int pageSize);
}
