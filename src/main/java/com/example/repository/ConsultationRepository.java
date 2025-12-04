package com.example.repository;

import com.example.model.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    // ANTES: findByPatientId (Spring intentaba adivinar y fallaba)
    // AHORA: findByAppointment_PatientId (Navega: Consultation.appointment -> Appointment.patientId)
    Page<Consultation> findByAppointment_PatientId(Long patientId, Pageable pageable);

    // Lo mismo para Doctor: Appointment -> Doctor (Objeto) -> DoctorId
    // Nota: Doctor SÍ es un objeto dentro de Appointment (porque son del mismo microservicio), 
    // así que aquí sí navegamos por objeto.
    Page<Consultation> findByAppointment_Doctor_DoctorId(Long doctorId, Pageable pageable);
    
    boolean existsByAppointment_AppointmentId(Long appointmentId);
    
    Consultation findByAppointment_AppointmentId(Long appointmentId);
}