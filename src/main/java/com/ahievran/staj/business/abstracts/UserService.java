package com.ahievran.staj.business.abstracts;

import com.ahievran.staj.core.result.DataResult;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.dto.request.ResetPasswordRequest;

public interface UserService {
    DataResult<?> findByEmail(String email);

    Result sendResetPasswordMail(String email);

    Result resetPassword(ResetPasswordRequest request);
}
