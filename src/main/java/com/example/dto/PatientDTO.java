package com.example.dto;
import lombok.Data;
import java.sql.Date;

@Data
public class PatientDTO {
    private Long pk_patient_id;
    private String firstName;
    private String paternalSurname;
    private String phone;
    // Agrega más campos si los necesitas mostrar
}