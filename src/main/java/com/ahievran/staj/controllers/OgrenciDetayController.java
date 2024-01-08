package com.ahievran.staj.controllers;

import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.dto.request.OgrenciDetayEkleRequest;
import com.ahievran.staj.dto.request.OgrenciGuncelleRequest;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profilim")
public class OgrenciDetayController {

    @PostMapping
    public Result ogrenciDetayEkle(OgrenciDetayEkleRequest request, Principal principal) {
        request.setEmail(principal.getName());
        return null;
    }

    @GetMapping("?userId={userId}")
    public Result ogrenciDetayGetir(@RequestParam(name = "userId") Long userId) {
        return null;
    }

    @PutMapping
    public Result ogrenciDetayGuncelle(OgrenciGuncelleRequest request) {
        return null;
    }
}
