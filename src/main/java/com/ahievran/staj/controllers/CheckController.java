package com.ahievran.staj.controllers;

import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.core.result.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/check")
@RequiredArgsConstructor
public class CheckController {

    @GetMapping("/check-token-expired")
    public ResponseEntity<Result> tokenGecerlilikKontrolEt(){
        return ResponseEntity.ok().body(new SuccessResult("Token geçerli",200));
    }
}


