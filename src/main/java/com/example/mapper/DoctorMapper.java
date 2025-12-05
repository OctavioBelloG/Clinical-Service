package com.example.mapper;

import com.example.dto.DoctorRequest;
import com.example.dto.DoctorResponse;
import com.example.model.Doctor;
import com.example.model.Specialty;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequest req) {
        Doctor doctor = new Doctor();
        doctor.setUserId(req.getUserId());
        doctor.setFullName(req.getFullName());
        doctor.setRegistrationNumber(req.getRegistrationNumber());
        doctor.setRating(req.getRating());
        
        // La relación de especialidad se suele setear en el servicio buscando por ID,
        // pero aquí dejamos la estructura básica.
        Specialty specialty = new Specialty();
        specialty.setSpecialtyId(req.getSpecialtyId());
        doctor.setSpecialty(specialty);
        
        return doctor;
    }

    public static DoctorResponse toResponse(Doctor doctor) {
        DoctorResponse res = new DoctorResponse();
        res.setDoctorId(doctor.getDoctorId());
        res.setUserId(doctor.getUserId());
        res.setFullName(doctor.getFullName());
        res.setRegistrationNumber(doctor.getRegistrationNumber());
        res.setRating(doctor.getRating());

        // --- CORRECCIÓN PARA QUE SALGA EL NOMBRE DE LA ESPECIALIDAD ---
        if (doctor.getSpecialty() != null) {
            // Aquí asignamos el nombre al campo que mapeamos con @JsonProperty("specialty")
            res.setSpecialtyName(doctor.getSpecialty().getName());
        }
        
        // NOTA SOBRE EL USUARIO:
        // Como el nombre del usuario vive en OTRO microservicio, 
        // aquí seguirá siendo null a menos que hagas una llamada extra para obtenerlo.
        // Gracias al @JsonInclude, si es null, simplemente no aparecerá.
        
        return res;
    }

    public static void copyToEntity(DoctorRequest req, Doctor doctor) {
        doctor.setFullName(req.getFullName());
        doctor.setRegistrationNumber(req.getRegistrationNumber());
        doctor.setRating(req.getRating());
        // Actualizar relaciones requeriría lógica extra en el servicio
    }
}
