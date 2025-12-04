package com.example.dto;
import lombok.Data;

@Data
public class UserDTO {
    private Long pk_user_id;
    private String username;
    private String email;
    private String role;
}