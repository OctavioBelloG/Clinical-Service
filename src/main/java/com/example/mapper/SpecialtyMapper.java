package com.example.mapper;

import com.example.model.Specialty;
import com.example.dto.SpecialtyRequest;
import com.example.dto.SpecialtyResponse;

public final class SpecialtyMapper {

    public static SpecialtyResponse toResponse(Specialty s) {
        if (s == null) return null;
        return SpecialtyResponse.builder()
                .specialtyId(s.getSpecialtyId())
                .name(s.getName())
                .description(s.getDescription())
                .build();
    }

    public static Specialty toEntity(SpecialtyRequest dto) {
        if (dto == null) return null;
        Specialty s = new Specialty();
        s.setName(dto.getName());
        s.setDescription(dto.getDescription());
        return s;
    }

    public static void copyToEntity(SpecialtyRequest dto, Specialty entity) {
        if (dto == null || entity == null) return;
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }
}
