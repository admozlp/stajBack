package com.ahievran.staj.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.staj.entities.Ogrenci;

import java.util.Optional;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long>{
    Optional<Ogrenci> findByUserId(Long userId);
}
