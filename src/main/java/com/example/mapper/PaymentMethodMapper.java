// src/main/java/com/example/mapper/PaymentMethodMapper.java
package com.example.mapper;

import com.example.model.PaymentMethod;
import com.example.dto.PaymentMethodRequest;
import com.example.dto.PaymentMethodResponse;

public final class PaymentMethodMapper {

    public static PaymentMethodResponse toResponse(PaymentMethod method) {
        if (method == null) return null;
        return PaymentMethodResponse.builder()
                .methodId(method.getMethodId())
                .methodName(method.getMethodName())
                .description(method.getDescription())
                .build();
    }

    public static PaymentMethod toEntity(PaymentMethodRequest dto) {
        if (dto == null) return null;
        PaymentMethod method = new PaymentMethod();
        method.setMethodName(dto.getMethodName());
        method.setDescription(dto.getDescription());
        return method;
    }

    public static void copyToEntity(PaymentMethodRequest dto, PaymentMethod entity) {
        if (dto == null || entity == null) return;
        entity.setMethodName(dto.getMethodName());
        entity.setDescription(dto.getDescription());
    }
}