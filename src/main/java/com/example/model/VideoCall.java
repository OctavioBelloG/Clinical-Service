package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "VIDEOCALLS")
public class VideoCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_videocall_id")
    private Long videocallId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_appointment_id", nullable = false)
    private Appointment appointment;

    @Column(name = "link", nullable = false, columnDefinition = "TEXT")
    private String link;

    @Column(name = "provider")
    private String provider; // 'zoom', 'meet', 'teams', etc.

    @Column(name = "status")
    private String status; // 'active', 'ended', 'cancelled'
}