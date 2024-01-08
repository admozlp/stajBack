package com.ahievran.staj.dto.auth.ogrenci;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

	 @NotNull
	 @NotBlank
	 @NotEmpty
	 @Size(min=10, max=200)
  	 private String email;
	 
	 @NotNull
	 @NotBlank
	 @NotEmpty
	 @Size(min=8, max=100)
	 String password;
}
