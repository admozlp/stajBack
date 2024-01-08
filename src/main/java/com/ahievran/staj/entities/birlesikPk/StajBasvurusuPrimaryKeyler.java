package com.ahievran.staj.entities.birlesikPk;

import java.io.Serializable;

import com.ahievran.staj.entities.BasvuruDonemi;
import com.ahievran.staj.entities.Ogrenci;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StajBasvurusuPrimaryKeyler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BasvuruDonemi basvuruDonemi;

	private Ogrenci ogrenci;
	
}
