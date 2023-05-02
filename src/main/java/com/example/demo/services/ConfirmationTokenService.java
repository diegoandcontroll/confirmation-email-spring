package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.ConfirmationToken;
import com.example.demo.repositories.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
  private final ConfirmationTokenRepository repository;

  public Optional<ConfirmationToken> getToken(String token) {
    return repository.findByToken(token);
  }

  public int setConfirmedAt(String token) {
    return repository.updateConfirmedAt(
        token, LocalDateTime.now());
  }

  public void save(ConfirmationToken confirmationToken) {
    repository.save(confirmationToken);
  }
}
