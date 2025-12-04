package com.example.service;

import com.example.dto.VideoCallRequest;
import com.example.dto.VideoCallResponse;

public interface VideoCallService {

    VideoCallResponse create(VideoCallRequest req);

    VideoCallResponse findByAppointmentId(Long appointmentId);

    VideoCallResponse updateStatus(Long videocallId, String status);
}