package com.ahievran.staj.business.concretes;

import com.ahievran.staj.business.abstracts.UserService;
import com.ahievran.staj.config.JwtService;
import com.ahievran.staj.core.result.DataResult;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.core.result.SuccessResult;
import com.ahievran.staj.dataAccess.TokenRepository;
import com.ahievran.staj.dataAccess.UserRepository;
import com.ahievran.staj.dto.request.ResetPasswordRequest;
import com.ahievran.staj.entities.Token;
import com.ahievran.staj.entities.User;
import com.ahievran.staj.exception.types.CustomExpiredJwtException;
import com.ahievran.staj.exception.types.UserNotFoundException;
import com.ahievran.staj.util.TokenService;
import com.ahievran.staj.util.mail.MailService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;




    @Override
    public DataResult<?> findByEmail(String email) {
        return new DataResult<>(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı")),true);
    }

    @Override
    public Result sendResetPasswordMail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

        var jwtToken = jwtService.generateToken(user);

        tokenService.revokeAllUserTokens(user);
        // user a token ekle
        tokenService.saveUserToken(user, jwtToken);

        // tokeni mail ile gonder
        mailService.sendResetMail(email, jwtToken);

        return new SuccessResult("Parola sıfırlama maili gönderildi", 200);
    }

    @Override
    public Result resetPassword(ResetPasswordRequest request) {
        // parola ve token geldi
        // token ile user çek
        Token token = tokenRepository.findByTokenAndExpiredAndRevoked(
                request.getToken(), false, false).orElseThrow(
                        () -> new CustomExpiredJwtException("Token süresi dolmuş"));

        User user = token.getUser();
        // user a yeni parolayı ekle
        user.setPassword(request.getPassword());
        // user ı kaydet
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        userRepository.save(user);

        return new SuccessResult("Parola başarıyla değiştirildi", 200);
    }
}
