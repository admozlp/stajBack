package com.ahievran.staj.business.rules;

import com.ahievran.staj.dataAccess.OgrenciDetayRepository;
import com.ahievran.staj.exception.types.OgrenciNoAlreadyExistsException;
import com.ahievran.staj.exception.types.TcNoAlreadyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OgrenciBusinessRules {
    private final OgrenciDetayRepository ogrenciDetayRepository;
    public void ogrenciNoKontrol(String ogrenciNo) {
        if (ogrenciDetayRepository.existsByOgrenciNumarasi(ogrenciNo)) {
            throw new OgrenciNoAlreadyExistsException("Öğrenci numarası 2 ile 20 karakter arasında olmalıdır");
        }
    }

    public void tcNoKontrol(String tcNo) {
        if (ogrenciDetayRepository.existsByTcNo(tcNo)) {
            throw new TcNoAlreadyException("TC numarası 11 karakter olmalıdır");
        }
    }

}
