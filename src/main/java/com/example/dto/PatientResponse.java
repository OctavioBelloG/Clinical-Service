package com.example.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;

@Data
@Builder
public class PatientResponse {
    private Long patientId;
    private String firstName;
    private String paternalSurname;
    private String maternalSurname;
    private Date birthDate;
    private String phone;
    // Agrega más campos si los necesitas mostrar
}