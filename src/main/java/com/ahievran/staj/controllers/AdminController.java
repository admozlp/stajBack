package com.ahievran.staj.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahievran.staj.dto.auth.sekreter.RegisterSekreterRequest;
import com.ahievran.staj.dto.auth.sekreter.RegisterSekreterResponse;


@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

	@PostMapping("/sekreter-ekle")
    public ResponseEntity<RegisterSekreterResponse> saveSekreter(@RequestBody RegisterSekreterRequest registerSekreterRequest) {
		System.out.println("admin");
		return null;
    }
}
