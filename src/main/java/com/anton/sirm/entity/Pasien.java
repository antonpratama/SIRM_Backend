package com.anton.sirm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pasien")
public class Pasien extends BaseEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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

    @OneToMany(mappedBy = "pasien")
    private List<RekamMedis> rekamMedis;
}
