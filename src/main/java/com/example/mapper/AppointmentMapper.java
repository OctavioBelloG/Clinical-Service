package com.example.mapper;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Schedule;
import com.example.dto.AppointmentRequest;
import com.example.dto.AppointmentResponse;

public final class AppointmentMapper {

    public static AppointmentResponse toResponse(Appointment a) {
        if (a == null) return null;
        return AppointmentResponse.builder()
                .appointmentId(a.getAppointmentId())
                // CAMBIO: Usamos el ID directo
                .patientId(a.getPatientId()) 
                .doctorId(a.getDoctor() != null ? a.getDoctor().getDoctorId() : null)
                .scheduleId(a.getSchedule() != null ? a.getSchedule().getScheduleId() : null)
                .date(a.getDate())
                .consultationType(a.getConsultationType())
                .status(a.getStatus())
                .build();
    }

    public static Appointment toEntity(AppointmentRequest dto) {
        if (dto == null) return null;
        Appointment a = new Appointment();
        
        // CAMBIO: Asignación directa de ID
        if (dto.getPatientId() != null) {
            a.setPatientId(dto.getPatientId());
        }

        if (dto.getDoctorId() != null) {
            Doctor d = new Doctor();
            d.setDoctorId(dto.getDoctorId());
            a.setDoctor(d);
        }
        if (dto.getScheduleId() != null) {
            Schedule s = new Schedule();
            s.setScheduleId(dto.getScheduleId());
            a.setSchedule(s);
        }
        a.setDate(dto.getDate());
        a.setConsultationType(dto.getConsultationType());
        a.setStatus(dto.getStatus());
        return a;
    }

    public static void copyToEntity(AppointmentRequest dto, Appointment entity) {
        if (dto == null || entity == null) return;
        
        // CAMBIO: Actualizamos el ID
        if (dto.getPatientId() != null) {
            entity.setPatientId(dto.getPatientId());
        }
        
        if (dto.getDoctorId() != null) {
            Doctor d = new Doctor();
            d.setDoctorId(dto.getDoctorId());
            entity.setDoctor(d);
        } else {
            entity.setDoctor(null);
        }
        if (dto.getScheduleId() != null) {
            Schedule s = new Schedule();
            s.setScheduleId(dto.getScheduleId());
            entity.setSchedule(s);
        } else {
            entity.setSchedule(null);
        }
        entity.setDate(dto.getDate());
        entity.setConsultationType(dto.getConsultationType());
        entity.setStatus(dto.getStatus());
    }
}