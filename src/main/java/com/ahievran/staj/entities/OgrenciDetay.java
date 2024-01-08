package com.ahievran.staj.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ogrenciDetaylari")
public class OgrenciDetay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ogrenciNumarasi", nullable = false, unique = true)
	@Size(min = 2, max = 20)
	private String ogrenciNumarasi;
	
	@Column(name = "sinif", nullable = false)
	private Integer sinif;
	
	@Column(name = "tcNo", nullable = false, unique = true)
	@Size(min = 11, max = 11)
	private String tcNo;
	
	@Column(name = "adres", nullable = false)
	@Size(min = 2, max = 400)
	private String adres;

	@Column(name = "telefon", nullable = false)
	@Size(min = 10, max = 11)
	private String telefon;
	
	@Column(name = "sgkKaydi", nullable = false)
	private boolean sgkKaydi;

	@CreatedDate
	@Column(name = "createDate", nullable = false, updatable = false)
	private LocalDateTime createDate;

	@LastModifiedDate
	@Column(name = "lastModifiedDate")
	private LocalDateTime lastModifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "ilceId")// nulable = false yapılacak unutma
	private Ilce ilce;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ogrenciId", referencedColumnName = "id", nullable = false, unique = true)
	private Ogrenci ogrenci;
}
