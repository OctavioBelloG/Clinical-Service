package com.example.repository;

import com.example.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Si usas query nativa
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    // Cambio: de findByPatient_PatientId a findByPatientId
    List<Payment> findByPatientId(Long patientId);
    
    Payment findByConsultation_ConsultationId(Long consultationId);
    
    boolean existsByConsultation_ConsultationId(Long consultationId);

    // Para las estadísticas (Query nativa o JPQL)
    @Query("SELECT p.method.methodName, SUM(p.amount) FROM Payment p GROUP BY p.method.methodName")
    List<Object[]> getPaymentStatisticsByMethod();
}