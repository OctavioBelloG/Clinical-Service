package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PAYMENT_METHODS")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_method_id")
    private Long methodId;

    @Column(name = "method_name", unique = true, nullable = false)
    private String methodName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}