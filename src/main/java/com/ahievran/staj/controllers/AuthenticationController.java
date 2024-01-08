package com.ahievran.staj.controllers;

import com.ahievran.staj.business.abstracts.EmailVerificationService;
import com.ahievran.staj.business.abstracts.OgrenciService;
import com.ahievran.staj.business.abstracts.UserService;
import com.ahievran.staj.business.services.AuthenticationService;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.dto.auth.ogrenci.AuthenticationRequest;
import com.ahievran.staj.dto.auth.ogrenci.OgrenciRegisterRequest;
import com.ahievran.staj.dto.request.ResetPasswordRequest;
import com.ahievran.staj.util.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

  private final AuthenticationService service;
  private final TokenService tokenService;
  private final OgrenciService ogrenciService;
  private final EmailVerificationService emailVerificationService;
  private final UserService userService;

  
  @PostMapping("/giris")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Result> authenticate(@RequestBody @Valid AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/ogrenci-kayit")
  public ResponseEntity<Result> saveOgrenci(@RequestBody @Valid OgrenciRegisterRequest ogrenciRegisterRequest){
    return ResponseEntity.ok().body(ogrenciService.register(ogrenciRegisterRequest));
  }

  @GetMapping("/mail-dogrula")
  public ResponseEntity<Result> mailDogrula(@RequestParam(name = "token") String token){
    return ResponseEntity.ok().body(emailVerificationService.verifyEmail(token));
  }

  @PostMapping("/token-yenileme")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    tokenService.refreshToken(request, response);
  }

  @GetMapping("/sifremi-unuttum-mail-gonder")
  public ResponseEntity<Result>  sifreYenilemeMailiGonder(@RequestParam(name="email") String email){
    return ResponseEntity.ok().body(userService.sendResetPasswordMail(email));
  }

  @PostMapping("/reset-password")
  public ResponseEntity<Result> resetPassword(@RequestBody @Valid ResetPasswordRequest request){
        return ResponseEntity.ok().body(userService.resetPassword(request));
  }




}
