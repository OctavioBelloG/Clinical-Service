package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
// 1. ESTA LÍNEA ES MÁGICA: Elimina cualquier campo que valga null
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class DoctorResponse {

    private Long doctorId;
    
    private Long userId;

    // Si este sigue siendo null, simplemente desaparecerá del JSON
    @JsonProperty("user") // Cambia "userName" a "user" en el JSON
    private String userName; 

    // 2. Aquí quitamos el ID de la especialidad si no lo quieres, 
    // y renombramos el nombre para que se vea limpio.
    @JsonProperty("specialty") // En el JSON saldrá como "specialty": "Cardiología"
    private String specialtyName;

    @JsonProperty("name") // En lugar de fullName, saldrá "name"
    private String fullName;

    private String registrationNumber;
    
    private BigDecimal rating;
}
