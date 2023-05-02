package com.example.demo.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.utils.EmailSender;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
  private final JavaMailSender mail;
  private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

  @Override
  @Async
  public void send(String to, String email) {
    try {

      MimeMessage mimeMessage = mail.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

      mimeMessageHelper.setText(email, true);
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject("Confirm your email!");
      mimeMessageHelper.setFrom("admin@email.com");
      mail.send(mimeMessage);

    } catch (MessagingException e) {
      logger.error("Failed to send mail", e);
      throw new IllegalStateException("Failed to send mail");
    }

  }

}
