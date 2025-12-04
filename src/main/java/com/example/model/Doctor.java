package com.example.model;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DOCTORS")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_doctor_id")
    private Long doctorId;

    // --- CAMBIO MICROSERVICIOS: Referencia por ID ---
    @Column(name = "fk_user_id", unique = true, nullable = false)
    private Long userId; 
    // -----------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_specialty_id", nullable = false)
    private Specialty specialty;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;

    @Column(name = "rating")
    private BigDecimal rating; 

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;
    
    // Este campo NO se guarda en BD, sirve para cuando traigamos datos del users-service
    @Transient 
    private Object userDetailsDTO; 
}