package com.example.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AppointmentRequest {
    private Long patientId;
    private Long doctorId;
    private Long scheduleId;
    private Timestamp date;
    private String consultationType;
    private String status;
}
