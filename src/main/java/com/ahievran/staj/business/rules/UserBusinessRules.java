package com.ahievran.staj.business.rules;

import org.springframework.stereotype.Service;

import com.ahievran.staj.dataAccess.UserRepository;
import com.ahievran.staj.entities.User;
import com.ahievran.staj.exception.types.EmailAlreadyExistsException;
import com.ahievran.staj.exception.types.EmailDogrulamaException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserBusinessRules {
	private final UserRepository repository;
	
	public void checkEmailExists(String email){
		if(repository.existsByEmail(email)) {
			throw new EmailAlreadyExistsException("Mail adresi başka hesap taraından kullanılmakta");
		}
	}

	public void checkEmailDogrulama(User user) {
		if(!user.isEmailDogrulama())
			throw new EmailDogrulamaException("Mail dogrulamasını gerçekleştiriniz");
	}
	
}
