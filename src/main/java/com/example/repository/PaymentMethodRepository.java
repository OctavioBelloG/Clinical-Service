// src/main/java/com/example/repository/PaymentMethodRepository.java
package com.example.repository;

import com.example.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    boolean existsByMethodNameIgnoreCase(String methodName);
}