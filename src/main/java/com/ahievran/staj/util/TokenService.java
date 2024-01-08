package com.ahievran.staj.util;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.ahievran.staj.config.JwtService;
import com.ahievran.staj.dataAccess.TokenRepository;
import com.ahievran.staj.dataAccess.UserRepository;
import com.ahievran.staj.dto.auth.ogrenci.AuthenticationResponse;
import com.ahievran.staj.entities.Token;
import com.ahievran.staj.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;
	private UserRepository repository;
	
	 public void saveUserToken(User user, String jwtToken) {
		    var token = Token.builder()
		        .user(user)
		        .token(jwtToken)
		        .tokenType(TokenType.BEARER)
		        .expired(false)
		        .revoked(false)
		        .build();
		    tokenRepository.save(token);
		  }

		  public void revokeAllUserTokens(User user) {
		    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		    if (validUserTokens.isEmpty())
		      return;
		    validUserTokens.forEach(token -> {
		      token.setExpired(true);
		      token.setRevoked(true);
		    });
		    tokenRepository.saveAll(validUserTokens);
		  }

		  public void refreshToken(
		          HttpServletRequest request,
		          HttpServletResponse response
		  ) throws IOException {
		    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		    final String refreshToken;
		    final String userEmail;
		    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
		      return;
		    }
		    refreshToken = authHeader.substring(7);
		    userEmail = jwtService.extractUsername(refreshToken);
		    if (userEmail != null) {
		      var user = this.repository.findByEmail(userEmail)
		              .orElseThrow(() -> new BadCredentialsException("Email veya parola hatalı"));
		      if (jwtService.isTokenValid(refreshToken, user)) {
		        var accessToken = jwtService.generateToken(user);
		        revokeAllUserTokens(user);
		        saveUserToken(user, accessToken);
		        var authResponse = AuthenticationResponse.builder()
		                .accessToken(accessToken)
		                .refreshToken(refreshToken)
		                .build();
		        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
		      }
		    }
		  }
}
