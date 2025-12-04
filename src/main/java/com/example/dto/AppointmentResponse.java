package com.example.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponse {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private Long scheduleId;
    private Timestamp date;
    private String consultationType;
    private String status;
}
