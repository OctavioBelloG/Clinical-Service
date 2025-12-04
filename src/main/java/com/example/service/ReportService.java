// src/main/java/com/example/service/ReportService.java
package com.example.service;

import com.example.dto.ReportRequest;
import com.example.dto.ReportResponse;
import java.util.List;
import java.util.Map;

public interface ReportService {
    // RUTA: POST /reports/generate
    ReportResponse generateReport(ReportRequest req);
    
    // RUTA: GET /reports/{id}
    ReportResponse findById(Long id);

    // RUTA: GET /reports/type/{type}
    List<ReportResponse> getReportsByType(String reportType);

    // RUTA: GET /reports/statistics
    Map<String, Long> getReportCountStatistics();
}