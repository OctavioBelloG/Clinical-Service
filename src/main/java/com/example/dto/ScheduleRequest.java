package com.example.dto;

import java.sql.Time;

import lombok.Data;

@Data
public class ScheduleRequest {
    private Long doctorId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;
}
