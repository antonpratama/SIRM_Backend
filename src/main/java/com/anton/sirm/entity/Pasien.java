package com.anton.sirm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pasien")
public class Pasien {

    @Id
    @Column(name = "id_pasien")
    private String idPasien;

    @Column(name = "nama_pasien")
    private String namaPasien;

    private String alamat;

    @Column(name = "tanggal_lahir")
    private Date tanggalLahir;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "nomor_kontak")
    private String nomorKontak;

    @Column(name = "foto_pasien")
    private byte[] fotoPasien;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "pasien")
    private List<RekamMedis> rekamMedis;
}
