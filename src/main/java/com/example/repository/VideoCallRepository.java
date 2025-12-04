package com.example.repository;

import com.example.model.VideoCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoCallRepository extends JpaRepository<VideoCall, Long> {

    // Buscar videollamada por appointment ID
    Optional<VideoCall> findByAppointment_AppointmentId(Long appointmentId);

    // Verificar si existe videollamada para un appointment
    boolean existsByAppointment_AppointmentId(Long appointmentId);
}