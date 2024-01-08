package com.ahievran.staj.business.abstracts;

import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.dto.request.OgrenciDetayEkleRequest;
import com.ahievran.staj.dto.request.OgrenciGuncelleRequest;

public interface OgrenciDetayService {
    Result ogrenciDetayEkle(OgrenciDetayEkleRequest request);

    Result ogrenciDetayGetir(Long userId);

    Result ogrenciDetayGuncelle(OgrenciGuncelleRequest request);
}
