package com.ahievran.staj.business.concretes;

import com.ahievran.staj.business.abstracts.EmailVerificationService;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.dataAccess.TokenRepository;
import com.ahievran.staj.dataAccess.UserRepository;
import com.ahievran.staj.entities.Token;
import com.ahievran.staj.entities.User;
import com.ahievran.staj.exception.types.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationManager implements EmailVerificationService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    @Override
    public Result verifyEmail(String token) {
        Token tokenFromDb = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotFoundException("Token bulunamadı"));

        User user = tokenFromDb.getUser();

        user.setEmailDogrulama(true);

        userRepository.save(user);

        return new Result(true,"Email adresi doğrulandı, giriş yapabilirsiniz", 200);
    }
}
