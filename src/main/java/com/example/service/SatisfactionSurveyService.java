// src/main/java/com/example/service/SatisfactionSurveyService.java
package com.example.service;

import com.example.dto.SatisfactionSurveyRequest;
import com.example.dto.SatisfactionSurveyResponse;
import java.util.List;
import java.util.Map;

public interface SatisfactionSurveyService {
    // RUTA: POST /surveys
    SatisfactionSurveyResponse create(SatisfactionSurveyRequest req);
    
    // RUTA: GET /surveys/consultation/{id}
    SatisfactionSurveyResponse getSurveyByConsultation(Long consultationId);

    // RUTA: GET /surveys/doctor/{id}
    List<SatisfactionSurveyResponse> getSurveysByDoctor(Long doctorId);

    // RUTA: GET /surveys/statistics
    Map<String, Double> getRatingStatistics();
}