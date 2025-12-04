package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
