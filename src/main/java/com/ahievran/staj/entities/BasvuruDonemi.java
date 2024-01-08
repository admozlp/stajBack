package com.ahievran.staj.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "basvuruDonemleri")
public class BasvuruDonemi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "donemAdi")
	private String donemAdi;
	
	@Column(name = "basvuruBaslangicTarihi")
	private Date basvuruBaslangicTarihi;
	
	@Column(name = "basvuruBitisTarihi")
	private Date basvuruBitisTarihi;
	
	@Column(name = "komisyonOnayBaslangicTarihi")
	private Date komisyonOnayBaslangicTarihi;
	
	@Column(name = "komisyonOnayBitisTarihi")
	private Date komisyonOnayBitisTarihi;
	
	@Column(name = "stajBaslangicTarihi")
	private Date stajBaslangicTarihi;
	
	@Column(name = "stajBitisTarihi")
	private Date stajBitisTarihi;
	
	@OneToMany(mappedBy = "basvuruDonemi", fetch = FetchType.LAZY)
	private List<StajBasvurusu> stajBasvurusulari;
}
