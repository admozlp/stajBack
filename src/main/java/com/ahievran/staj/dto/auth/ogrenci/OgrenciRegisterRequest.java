package com.ahievran.staj.dto.auth.ogrenci;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OgrenciRegisterRequest {
	 @NotNull
	 @NotBlank
	 @NotEmpty
	 @Size(min=2, max=100)
	 private String firstname;
	 
	 @NotNull
	 @NotBlank
	 @NotEmpty
	 @Size(min=2, max=100)
	 private String lastname;

	 @NotNull
	 @NotBlank
	 @NotEmpty
	 @Size(min=10, max=200)
	 @Pattern(regexp="^[a-zA-Z0-9._%+-]+@ogr\\.ahievran\\.edu\\.tr$", message="Geçerli bir ahievran.edu.tr e-posta adresi girin")
	 private String email;

	 @NotNull
	 @NotBlank
	 @NotEmpty
	 @Size(min=8, max=100)
	 //@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]])(?=.*[!@#$%^&*])(?=.{8,})$", message="Şifreniz en az 8 karakter uzunluğunda olmalıdır ve en az bir büyük harf, bir küçük harf, bir sayı ve bir özel karakter içermelidir")
	 private String password;
}
