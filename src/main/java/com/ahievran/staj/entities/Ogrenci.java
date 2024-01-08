package com.ahievran.staj.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Table(name = "ogrenciler")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ogrenci {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
		
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "ogrenci", fetch = FetchType.LAZY)
	private OgrenciDetay ogrenciDetay;
	
    @OneToMany(mappedBy = "ogrenci", fetch = FetchType.LAZY)
    private List<StajBasvurusu> stajBasvuruulari;
    
    @ManyToMany(mappedBy = "ogrenciler",fetch = FetchType.LAZY)
    private Set<Program> programlar;
}
