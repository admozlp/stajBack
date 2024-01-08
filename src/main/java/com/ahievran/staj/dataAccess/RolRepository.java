package com.ahievran.staj.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.staj.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
	Optional<Rol> findByRol(String rol);
}
