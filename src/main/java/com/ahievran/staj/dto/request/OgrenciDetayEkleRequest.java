package com.ahievran.staj.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OgrenciDetayEkleRequest {
    private String email;
    @NotNull
    @Size(min = 2, max = 20)
    private String ogrenciNumarasi;
    @NotNull
    private Integer sinif;
    @NotNull
    @Size(min = 11, max = 11)
    private String tcNo;
    @NotNull
    @Size(min = 2, max = 400)
    private String adres;
    @NotNull
    @Size(min = 10, max = 11)
    private String telefon;
    @NotNull
    private boolean sgkKaydi;
    @NotNull
    private Long ilce;
    @NotNull
    private Long program;
}
