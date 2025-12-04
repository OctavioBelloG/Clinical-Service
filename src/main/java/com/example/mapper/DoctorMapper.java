package com.example.mapper;

import com.example.model.Doctor;
import com.example.model.Specialty;
import com.example.dto.DoctorRequest;
import com.example.dto.DoctorResponse;

public final class DoctorMapper {

    public static DoctorResponse toResponse(Doctor d) {
        if (d == null) return null;
        return DoctorResponse.builder()
                .doctorId(d.getDoctorId())
                // CAMBIO: ID directo
                .userId(d.getUserId())
                .specialtyId(d.getSpecialty() != null ? d.getSpecialty().getSpecialtyId() : null)
                .fullName(d.getFullName())
                .registrationNumber(d.getRegistrationNumber())
                .rating(d.getRating())
                .build();
    }

    public static Doctor toEntity(DoctorRequest dto) {
        if (dto == null) return null;
        Doctor d = new Doctor();
        d.setFullName(dto.getFullName());
        d.setRegistrationNumber(dto.getRegistrationNumber());
        d.setRating(dto.getRating());
        
        // CAMBIO: Asignación directa de ID de usuario
        if (dto.getUserId() != null) {
            d.setUserId(dto.getUserId());
        }

        if (dto.getSpecialtyId() != null) {
            Specialty s = new Specialty();
            s.setSpecialtyId(dto.getSpecialtyId());
            d.setSpecialty(s);
        }
        return d;
    }

    public static void copyToEntity(DoctorRequest dto, Doctor entity) {
        if (dto == null || entity == null) return;
        entity.setFullName(dto.getFullName());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setRating(dto.getRating());
        
        // CAMBIO: Actualización de ID
        if (dto.getUserId() != null) {
            entity.setUserId(dto.getUserId());
        }
        
        if (dto.getSpecialtyId() != null) {
            Specialty s = new Specialty();
            s.setSpecialtyId(dto.getSpecialtyId());
            entity.setSpecialty(s);
        } else {
            entity.setSpecialty(null);
        }
    }
}