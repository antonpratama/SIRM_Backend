package com.anton.sirm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dokter")
public class Dokter extends BaseEntity{

    @Id
    @Column(name = "id_dokter")
    private String idDokter;

    private String password;

    @Column(name = "nama_dokter")
    private String namaDokter;

    private String spesialisasi;

    @Column(name = "nomor_lisensi")
    private String nomorLisensi;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

    @OneToMany(mappedBy = "dokter")
    private List<RekamMedis> rekamMedis;
}
