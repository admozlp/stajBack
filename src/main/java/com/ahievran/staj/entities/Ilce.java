package com.ahievran.staj.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ilceler")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ilce {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ad")
	private String ad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ilId")
	private Il il;
	
	@OneToMany(mappedBy = "ilce", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OgrenciDetay> ogrenciDetaylari;
}
