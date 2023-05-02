package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

  @SequenceGenerator(name = "confirmation_token_sequence", sequenceName = "confirmation_token_sequence", allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
  private Long id;

  @Column(nullable = false)
  private String token;

  private LocalDateTime createdAt;

  private LocalDateTime expiresAt;

  private LocalDateTime confirmedAt;

  @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

  public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, LocalDateTime confirmedAt,
      AppUser appuser) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiresAt = expiredAt;
    this.confirmedAt = confirmedAt;
    this.appUser = appuser;
  }
}
