package com.anton.sirm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasienResponse {

    private String idPasien;

    private String namaPasien;

    private String alamat;

    private Date tanggalLahir;

    private String jenisKelamin;

    private String nomorKontak;

    private byte[] fotoPasien;
}
