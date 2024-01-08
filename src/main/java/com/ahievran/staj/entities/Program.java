package com.ahievran.staj.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programlar")
public class Program {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ad", nullable = false, unique = true)
	@Size(min = 2, max = 200)
	private String ad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bolumId")
	private Bolum bolum;
	
    @OneToOne(mappedBy = "program", fetch = FetchType.LAZY)
	private Sekreter sekreter;
    
    @OneToOne(mappedBy = "program", fetch = FetchType.LAZY)
    private Kurul kurul;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "programOgrenci",
    	joinColumns = @JoinColumn(name = "programId"),
    	inverseJoinColumns = @JoinColumn(name = "ogrenciId"))
    private Set<Ogrenci> ogrenciler;
}
