package com.example.mapper;

import com.example.model.Schedule;
import com.example.model.Doctor;
import com.example.dto.ScheduleRequest;
import com.example.dto.ScheduleResponse;

public final class ScheduleMapper {

    public static ScheduleResponse toResponse(Schedule s) {
        if (s == null) return null;
        return ScheduleResponse.builder()
                .scheduleId(s.getScheduleId())
                .doctorId(s.getDoctor() != null ? s.getDoctor().getDoctorId() : null)
                .dayOfWeek(s.getDayOfWeek())
                .startTime(s.getStartTime())
                .endTime(s.getEndTime())
                .build();
    }

    public static Schedule toEntity(ScheduleRequest dto) {
        if (dto == null) return null;
        Schedule s = new Schedule();
        s.setDayOfWeek(dto.getDayOfWeek());
        s.setStartTime(dto.getStartTime());
        s.setEndTime(dto.getEndTime());
        if (dto.getDoctorId() != null) {
            Doctor d = new Doctor();
            d.setDoctorId(dto.getDoctorId());
            s.setDoctor(d);
        }
        return s;
    }

    public static void copyToEntity(ScheduleRequest dto, Schedule entity) {
        if (dto == null || entity == null) return;
        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        if (dto.getDoctorId() != null) {
            Doctor d = new Doctor();
            d.setDoctorId(dto.getDoctorId());
            entity.setDoctor(d);
        } else {
            entity.setDoctor(null);
        }
    }
}
