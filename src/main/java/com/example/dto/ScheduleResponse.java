package com.example.dto;

import java.sql.Time;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleResponse {
    private Long scheduleId;
    private Long doctorId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;
}
