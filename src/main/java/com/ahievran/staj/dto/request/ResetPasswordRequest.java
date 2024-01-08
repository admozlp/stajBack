package com.ahievran.staj.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    @NonNull
    private String token;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min=8, max=100)
    //@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]])(?=.*[!@#$%^&*])(?=.{8,})$", message="Şifreniz en az 8 karakter uzunluğunda olmalıdır ve en az bir büyük harf, bir küçük harf, bir sayı ve bir özel karakter içermelidir")
    private String password;
}
