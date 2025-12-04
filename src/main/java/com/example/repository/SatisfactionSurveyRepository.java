package com.example.repository;

import com.example.model.SatisfactionSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatisfactionSurveyRepository extends JpaRepository<SatisfactionSurvey, Long> {

    // Cambio: Buscamos por el ID directo que pusimos en la entidad
    List<SatisfactionSurvey> findByPatientId(Long patientId);
    
    SatisfactionSurvey findByConsultation_ConsultationId(Long consultationId);
    
    boolean existsByConsultation_ConsultationId(Long consultationId);

    // NOTA: Para buscar por Doctor, como la encuesta NO tiene doctorId directo (está en Consultation->Appointment->Doctor),
    // aquí SÍ usamos el caminito largo (underscore):
    List<SatisfactionSurvey> findByConsultation_Appointment_Doctor_DoctorId(Long doctorId);

    @Query("SELECT s.consultation.appointment.doctor.fullName, AVG(s.rating) FROM SatisfactionSurvey s GROUP BY s.consultation.appointment.doctor.fullName")
    List<Object[]> getAverageRatingByDoctor();
}