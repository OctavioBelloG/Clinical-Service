package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "PAYMENTS")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_payment_id")
    private Long paymentId;

    // --- CAMBIO MICROSERVICIOS ---
    @Column(name = "fk_patient_id", nullable = false)
    private Long patientId;
    // -----------------------------

    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "fk_consultation_id", nullable = false, unique = true)
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_method_id")
    private PaymentMethod method;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "payment_date")
    private Timestamp paymentDate;
}