package com.example.mapper;

import com.example.model.Report;
import com.example.dto.ReportRequest;
import com.example.dto.ReportResponse;

public final class ReportMapper {

    public static ReportResponse toResponse(Report r) {
        if (r == null) return null;
        return ReportResponse.builder()
                .reportId(r.getReportId())
                .reportType(r.getReportType())
                // CAMBIO: ID directo
                .generatedByUserId(r.getGeneratedByUserId())
                // CAMBIO: No tenemos el nombre de usuario (username) aquí. Se llena en Service.
                .generatedByUserName(null) 
                .generatedAt(r.getGeneratedAt())
                .dataJson(r.getData()) 
                .build();
    }

    public static Report toEntity(ReportRequest dto) {
        if (dto == null) return null;
        Report r = new Report();
        
        // CAMBIO: ID directo
        if (dto.getGeneratedByUserId() != null) {
            r.setGeneratedByUserId(dto.getGeneratedByUserId());
        }
        
        r.setReportType(dto.getReportType());
        return r;
    }

    public static void copyToEntity(ReportRequest dto, Report entity) {
        if (dto == null || entity == null) return;
        
        // CAMBIO: ID directo
        if (dto.getGeneratedByUserId() != null) {
            entity.setGeneratedByUserId(dto.getGeneratedByUserId());
        }
        
        entity.setReportType(dto.getReportType());
    }
}