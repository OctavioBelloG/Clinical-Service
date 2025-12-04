package com.example.mapper;

import com.example.dto.VideoCallRequest;
import com.example.dto.VideoCallResponse;
import com.example.model.Appointment;
import com.example.model.VideoCall;

public final class VideoCallMapper {

    private VideoCallMapper() {}

    public static VideoCallResponse toResponse(VideoCall videoCall) {
        if (videoCall == null) return null;

        return VideoCallResponse.builder()
                .videocallId(videoCall.getVideocallId())
                .appointmentId(videoCall.getAppointment() != null ?
                        videoCall.getAppointment().getAppointmentId() : null)
                
                // CAMBIO: Nombres a null (no tenemos Patient ni User aquí)
                .patientName(null)
                .doctorName(videoCall.getAppointment() != null &&
                        videoCall.getAppointment().getDoctor() != null ?
                        videoCall.getAppointment().getDoctor().getFullName() : null)
                
                .link(videoCall.getLink())
                .provider(videoCall.getProvider())
                .status(videoCall.getStatus())
                .build();
    }

    public static VideoCall toEntity(VideoCallRequest dto) {
        if (dto == null) return null;

        VideoCall videoCall = new VideoCall();

        if (dto.getAppointmentId() != null) {
            Appointment appointment = new Appointment();
            appointment.setAppointmentId(dto.getAppointmentId());
            videoCall.setAppointment(appointment);
        }

        videoCall.setLink(dto.getLink());
        videoCall.setProvider(dto.getProvider());
        videoCall.setStatus(dto.getStatus() != null ? dto.getStatus() : "active");

        return videoCall;
    }

    public static void copyToEntity(VideoCallRequest dto, VideoCall entity) {
        if (dto == null || entity == null) return;

        if (dto.getAppointmentId() != null) {
            Appointment appointment = new Appointment();
            appointment.setAppointmentId(dto.getAppointmentId());
            entity.setAppointment(appointment);
        }

        entity.setLink(dto.getLink());
        entity.setProvider(dto.getProvider());
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
    }
}