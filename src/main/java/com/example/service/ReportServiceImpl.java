package com.example.service;

import com.example.dto.ReportRequest;
import com.example.dto.ReportResponse;
import com.example.mapper.ReportMapper;
import com.example.model.Report;
import com.example.repository.ReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;
    // CAMBIO: No UserRepository
    private final ExternalUserService externalUserService;
    private final ObjectMapper objectMapper; 

    @Override
    public ReportResponse generateReport(ReportRequest req) {
        // CAMBIO: Validación remota
        if (!externalUserService.existsUserById(req.getGeneratedByUserId())) {
            throw new EntityNotFoundException("User not found (External check).");
        }
        
        // Simulación de datos
        Map<String, Object> data = Map.of(
            "report_name", req.getReportType(),
            "date_range", req.getFilterDateStart() + " to " + req.getFilterDateEnd(),
            "total_records", (int)(Math.random() * 1000)
        );
        String jsonData;
        try {
            jsonData = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report data.", e);
        }
        
        Report report = ReportMapper.toEntity(req);
        report.setData(jsonData);
        report.setGeneratedAt(new Timestamp(System.currentTimeMillis()));

        Report saved = repository.save(report);
        return ReportMapper.toResponse(saved);
    }
    
    @Override
    public List<ReportResponse> getReportsByType(String reportType) {
        return repository.findByReportTypeIgnoreCase(reportType).stream()
                .map(ReportMapper::toResponse)
                .toList();
    }
    
    @Override
    public Map<String, Long> getReportCountStatistics() {
        return repository.getReportCountByType().stream()
                .collect(Collectors.toMap(
                    obj -> (String) obj[0],
                    obj -> (Long) obj[1]
                ));
    }
    
    @Override
    public ReportResponse findById(Long id) {
        Report report = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found: " + id));
        return ReportMapper.toResponse(report);
    }
}