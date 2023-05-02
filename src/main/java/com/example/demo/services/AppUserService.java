package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.AppUser;
import com.example.demo.domain.ConfirmationToken;
import com.example.demo.repositories.AppuserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{
  private final AppuserRepository appuserRepository;
  
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final ConfirmationTokenService confirmationTokenService;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return appuserRepository.findByEmail(email)
    .orElseThrow(() -> 
      new UsernameNotFoundException("NOT FOUND USER")
    );
  }

  public String signUpUser(AppUser user){
    boolean present = appuserRepository.findByEmail(user.getEmail()).isPresent();
    if(present){
      throw new IllegalStateException("User by email exist");
    }
    String encode = bCryptPasswordEncoder.encode(user.getPassword());

    user.setPassword(encode);
    user.setLocked(false);
    user.setEnabled(false);
    appuserRepository.save(user);

    ConfirmationToken confirmationToken = new ConfirmationToken();
    confirmationToken.setAppUser(user);
    String stringToken = UUID.randomUUID().toString();
    confirmationToken.setToken(stringToken);
    confirmationToken.setCreatedAt(LocalDateTime.now());
    confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));

    confirmationTokenService.save(confirmationToken);
    return stringToken;
  }
  public int enableAppUser(String email) {
    return appuserRepository.enableAppUser(email);
}
  
}
