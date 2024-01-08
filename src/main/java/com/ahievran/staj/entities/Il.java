package com.ahievran.staj.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
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


@Table(name = "iller")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Il {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ad")
	private String ad;
	
	@OneToMany(mappedBy = "il",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Ilce> ilceler;
}
