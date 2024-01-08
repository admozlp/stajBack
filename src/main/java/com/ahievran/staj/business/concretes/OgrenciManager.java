package com.ahievran.staj.business.concretes;

import java.util.ArrayList;
import java.util.List;

import com.ahievran.staj.core.result.DataResult;
import com.ahievran.staj.core.result.SuccessResult;
import com.ahievran.staj.util.mail.MailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahievran.staj.business.abstracts.OgrenciService;
import com.ahievran.staj.business.rules.UserBusinessRules;
import com.ahievran.staj.config.JwtService;
import com.ahievran.staj.core.mappers.ModelMapperService;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.dataAccess.OgrenciRepository;
import com.ahievran.staj.dataAccess.RolRepository;
import com.ahievran.staj.dto.auth.ogrenci.OgrenciRegisterRequest;
import com.ahievran.staj.entities.Ogrenci;
import com.ahievran.staj.entities.Rol;
import com.ahievran.staj.entities.User;
import com.ahievran.staj.exception.types.RolNotFoundException;
import com.ahievran.staj.util.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class OgrenciManager implements OgrenciService{
	private final UserBusinessRules businessRules;
	private final ModelMapperService mapperService;
	private final OgrenciRepository ogrenciRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final TokenService tokenService;
	private final RolRepository rolRepository;
	private final MailService emailService;
	@Override
	public Result register(OgrenciRegisterRequest ogrenciRegisterReguest) {
	  	businessRules.checkEmailExists(ogrenciRegisterReguest.getEmail());
	  	
	  	User savedUser = mapperService.forRequest().map(ogrenciRegisterReguest,User.class);

		savedUser.setPassword(passwordEncoder.encode(ogrenciRegisterReguest.getPassword()));

		List<Rol> rollerList = new ArrayList<>();
	  		  
		Rol rol = rolRepository.findByRol("OGRENCI").orElseThrow(() -> new RolNotFoundException("OGRENCI rolu mevcut degil veritabnina eklemeniz gerekmektedir"));
	  	
	  	rollerList.add(rol);
	  	
	  	savedUser.setRoller(rollerList);

	  	Ogrenci ogrenci = Ogrenci.builder()
	  			.user(savedUser)
	  			.build();

	    var jwtToken = jwtService.generateToken(savedUser );

		emailService.sendVerificationMail(savedUser.getFirstname(), savedUser.getEmail(), jwtToken);

		ogrenciRepository.save(ogrenci);

		tokenService.saveUserToken(savedUser,  jwtToken);

	    return new SuccessResult("Kayıt işlemi başarılı", 200);
	}

	@Override
	public DataResult<?> findByUserIdForOtherService(Long id) {
		return new DataResult<>(ogrenciRepository.findByUserId(id), true);
	}

}
