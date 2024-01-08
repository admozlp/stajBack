package com.ahievran.staj.util.mail;

import com.ahievran.staj.exception.types.EmailSendingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromEmail;


    public void sendVerificationMail(String name, String to, String token) {
        try {
            Context context = new Context();
            context.setVariables(Map.of("name", name, "verificationUrl", EmailUtil.getVerificationUrl(host, token)));
            String body = templateEngine.process("emailtemplate", context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
            helper.setPriority(1);
            helper.setSubject("Hesap Doğrulama");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailSendingException("Mail gönderilemedi lütfen daha sonra tekrar deneyiniz");
        }
    }

    public void sendResetMail(String to, String token) {
        try {
            Context context = new Context();
            context.setVariables(Map.of("verificationUrl", EmailUtil.getResetUrl(host, token)));
            String body = templateEngine.process("resetpasswordtemplate", context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
            helper.setPriority(1);
            helper.setSubject("Parola yenileme");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailSendingException("Mail gönderilemedi lütfen daha sonra tekrar deneyiniz");
        }
    }
}
