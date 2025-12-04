// src/main/java/com/example/repository/ReportRepository.java
package com.example.repository;

import com.example.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    
    // RUTA: GET /reports/type/{type}
    List<Report> findByReportTypeIgnoreCase(String reportType);

    // RUTA: GET /reports/statistics - Conteo de reportes por tipo
    @Query("SELECT r.reportType, COUNT(r) FROM Report r GROUP BY r.reportType")
    List<Object[]> getReportCountByType();
}