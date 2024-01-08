package com.ahievran.staj.business.abstracts;

import com.ahievran.staj.core.result.Result;

public interface EmailVerificationService {
    Result verifyEmail(String token);
}
