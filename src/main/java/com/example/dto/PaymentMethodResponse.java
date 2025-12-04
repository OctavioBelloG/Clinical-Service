// src/main/java/com/example/dto/PaymentMethodResponse.java
package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentMethodResponse {
     @JsonProperty("Id of method")
    Long methodId;
     @JsonProperty("Name of method")
    String methodName;
    @JsonProperty("description")
    String description;
}