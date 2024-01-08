package com.ahievran.staj.dataAccess;

import com.ahievran.staj.entities.OgrenciDetay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgrenciDetayRepository extends JpaRepository<OgrenciDetay, Long> {
    boolean existsByOgrenciId(Long ogrenciId);

    boolean existsByOgrenciNumarasi(String ogrenciNumarasi);

    boolean existsByTcNo(String tcNo);
}
