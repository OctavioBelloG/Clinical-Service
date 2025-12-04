package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.model.Doctor;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
// RUTA: GET /doctors/pagination?page=&size=
    Page<Doctor> findAll(Pageable pageable);

    // RUTA: GET /doctors/specialty/{id}?page=&size=
    Page<Doctor> findBySpecialty_SpecialtyId(Long specialtyId, Pageable pageable);

    // RUTA: GET /doctors/top-rated?limit=10 (Limitamos con Pageable)
    List<Doctor> findTopByOrderByRatingDesc(Pageable pageable);

    // NUEVO: Para validar unicidad del registration number en el servicio
    boolean existsByRegistrationNumber(String registrationNumber);

}
