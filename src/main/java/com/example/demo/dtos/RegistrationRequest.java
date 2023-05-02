package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
  private String name;

  private String username;

  private String email;

  private String password;
}
