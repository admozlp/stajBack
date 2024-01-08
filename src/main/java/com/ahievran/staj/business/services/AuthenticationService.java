package com.ahievran.staj.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ahievran.staj.dataAccess.OgrenciDetayRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.ahievran.staj.business.rules.UserBusinessRules;
import com.ahievran.staj.config.JwtService;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.core.result.SuccessDataResult;
import com.ahievran.staj.dataAccess.UserRepository;
import com.ahievran.staj.dto.auth.ogrenci.AuthenticationRequest;
import com.ahievran.staj.dto.auth.ogrenci.AuthenticationResponse;
import com.ahievran.staj.util.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final UserBusinessRules businessRules;
  private final OgrenciDetayRepository ogrenciDetayRepository;
  

  public Result authenticate(AuthenticationRequest request) {
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new BadCredentialsException("Email veya parola hatalı"));

    businessRules.checkEmailDogrulama(user);

    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );
    }catch (Exception e) {
        throw new BadCredentialsException("Email veya parola hatalı");
    }

	    
    var jwtToken = jwtService.generateToken(user);
    
    var refreshToken = jwtService.generateRefreshToken(user);
    
    tokenService.revokeAllUserTokens(user);
    
    tokenService.saveUserToken(user, jwtToken);

    //io.jsonwebtoken.ExpiredJwtException: JWT strings must contain exactly 2 period characters. Found: 0
    // bu hatanın sebnebi nedir :
    
    AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .code(200)
                .build();
    
    List<String> roller = new ArrayList<>();
    
    user.getRoller().stream().map(rol -> roller.add(rol.getRol())).collect(Collectors.toList());

    if(roller.contains("OGRENCI")) {
      Long ogrenciId = user.getOgrenci().getId();
      List<Boolean> ogrenciDetay = new ArrayList<Boolean>();
      ogrenciDetay.add(ogrenciDetayRepository.existsByOgrenciId(ogrenciId));
      response.setOgrenciDetay(ogrenciDetay);
    }
    
    response.setRoller(roller);
    
    return new SuccessDataResult<AuthenticationResponse>(response,"Giriş başarılı", 200);
  }
}
