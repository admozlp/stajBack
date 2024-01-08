package com.ahievran.staj.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roller")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "rol", nullable = false)
	private String rol;
	
	@ManyToMany(mappedBy = "roller", fetch = FetchType.LAZY)
	private List<User> users;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rolYetki",
	joinColumns = @JoinColumn(name = "rolId"),
	inverseJoinColumns =  @JoinColumn(name = "yetkiId")
	)
	private List<Yetki> yetkiler;
	
	@Transactional 
	public List<SimpleGrantedAuthority> getAuthorities() {
		  List<Yetki> yetkiler = getYetkiler();
		  
		  var authorities =  yetkiler
		  .stream()
		  .map(yetki -> new SimpleGrantedAuthority(yetki.getYetki()))
		  .collect(Collectors.toList());
		  
		    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.getRol()));
		    return authorities;
	  }
	
}
