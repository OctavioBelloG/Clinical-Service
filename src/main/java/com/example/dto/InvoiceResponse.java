package com.example.dto;

import lombok.Builder;
import lombok.Value;
import java.sql.Timestamp;

@Value
@Builder
public class InvoiceResponse {
    Long invoiceId;
    
    // FK
    Long paymentId;
    
    String invoiceNumber;
    Timestamp issueDate;
}