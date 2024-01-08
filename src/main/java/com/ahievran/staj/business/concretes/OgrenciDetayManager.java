package com.ahievran.staj.business.concretes;

import com.ahievran.staj.business.abstracts.OgrenciDetayService;
import com.ahievran.staj.business.rules.OgrenciBusinessRules;
import com.ahievran.staj.core.mappers.ModelMapperService;
import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.core.result.SuccessResult;
import com.ahievran.staj.dataAccess.OgrenciDetayRepository;
import com.ahievran.staj.dataAccess.OgrenciRepository;
import com.ahievran.staj.dataAccess.UserRepository;
import com.ahievran.staj.dto.request.OgrenciDetayEkleRequest;
import com.ahievran.staj.dto.request.OgrenciGuncelleRequest;
import com.ahievran.staj.entities.Ogrenci;
import com.ahievran.staj.entities.OgrenciDetay;
import com.ahievran.staj.entities.User;
import com.ahievran.staj.exception.types.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OgrenciDetayManager implements OgrenciDetayService {
    private final UserRepository userRepository;
    private final OgrenciRepository ogrenciRepository;
    private final OgrenciDetayRepository ogrenciDetayRepository;
    private final ModelMapperService modelMapperService;
    private final OgrenciBusinessRules ogrenciBusinessRules;
    @Override
    public Result ogrenciDetayEkle(OgrenciDetayEkleRequest request) {
        ogrenciBusinessRules.ogrenciNoKontrol(request.getOgrenciNumarasi());

        ogrenciBusinessRules.tcNoKontrol(request.getTcNo());

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

        Ogrenci ogrenci = ogrenciRepository.findByUserId(user.getId()).orElseThrow(() -> new UserNotFoundException("Öğrenci bulunamadı"));

        OgrenciDetay ogrenciDetay = modelMapperService.forRequest()
                .map(request, OgrenciDetay.class);

        ogrenciDetay.setOgrenci(ogrenci);

        ogrenciDetayRepository.save(ogrenciDetay);

        return new SuccessResult("Öğrenci detayı eklendi",200);
    }

    @Override
    public Result ogrenciDetayGetir(Long userId) {
        return null;
    }

    @Override
    public Result ogrenciDetayGuncelle(OgrenciGuncelleRequest request) {
        return null;
    }
}
