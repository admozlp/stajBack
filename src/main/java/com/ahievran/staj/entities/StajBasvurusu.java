package com.ahievran.staj.entities;

import com.ahievran.staj.entities.birlesikPk.StajBasvurusuPrimaryKeyler;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stajBasvurulari")
@IdClass(StajBasvurusuPrimaryKeyler.class)
public class StajBasvurusu {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "basvuruDonemId")
	private BasvuruDonemi basvuruDonemi;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ogrenciId")
	private Ogrenci ogrenci;
}
