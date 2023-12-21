package com.anton.sirm.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasienRequest {

    @NotBlank
    private String idPasien;

    @NotBlank
    @Size(max = 100)
    private String namaPasien;

    @NotBlank
    @Size(max = 100)
    private String alamat;

    @NotBlank
    private Date tanggalLahir;

    @NotBlank
    @Size(max = 100)
    private String jenisKelamin;

    @NotBlank
    @Size(max = 100)
    private String nomorKontak;

    private byte[] fotoPasien;
}
