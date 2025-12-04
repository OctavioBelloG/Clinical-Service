package com.example.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorResponse {
   private Long doctorId;
    private Long userId;
    private String userName; // Información adicional
    private Long specialtyId;
    private String specialtyName; // Información adicional
    private String fullName;
    private String registrationNumber;
    private BigDecimal rating;
}
