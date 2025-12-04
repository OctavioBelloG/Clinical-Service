package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_appointment_id")
    private Long appointmentId;

    // --- CAMBIO MICROSERVICIOS: Referencia por ID ---
    @Column(name = "fk_patient_id", nullable = false)
    private Long patientId; 
    // -----------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_schedule_id")
    private Schedule schedule;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "consultation_type")
    private String consultationType;

    @Column(name = "status")
    private String status;
}