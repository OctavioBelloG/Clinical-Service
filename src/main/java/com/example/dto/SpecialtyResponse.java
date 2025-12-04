package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecialtyResponse {
    private Long specialtyId;
    private String name;
    private String description;
}
