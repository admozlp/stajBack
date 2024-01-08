package com.ahievran.staj.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;
  
	@Column(nullable = false)
    @Size(min = 2, max = 100)
	private String firstname;

	@Column(nullable = false)
    @Size(min = 2, max = 100)
	private String lastname;
  
	@Column(name = "email", unique = true, nullable = false)
    @Size(min = 10, max = 200)
	private String email;
  
	@Column(nullable = false)
    @Size(min = 8, max = 100)
    private String password;

    @Column(nullable = false)
	private boolean emailDogrulama = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRol",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn(name = "rolId"))
    private List<Rol> roller;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Token> tokens;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Ogrenci ogrenci;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Sekreter sekreter;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private KurulUyesi kurulUyesi;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private KurulBaskani kurulBaskani;
	
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
	  List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		try {
			   for(Rol rol : this.roller) {
				   List<SimpleGrantedAuthority> donenListe = rol.getAuthorities();
				   authorities.addAll(donenListe);
			   }
		}catch (Exception e) {
			System.out.println("hata yeri :" + e.getLocalizedMessage());
		}
	  return authorities;
  }
  
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.emailDogrulama;
  }
}
