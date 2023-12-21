package com.anton.sirm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rekam_medis")
public class RekamMedis extends BaseEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String idRekamMedis;

    @ManyToOne
    @JoinColumn(name = "id_resep_obat", referencedColumnName = "id_resep_obat")
    private ResepObat resepObat;

    @ManyToOne
    @JoinColumn(name = "id_pasien", referencedColumnName = "id_pasien")
    private Pasien pasien;

    @ManyToOne
    @JoinColumn(name = "id_dokter", referencedColumnName = "id_dokter")
    private Dokter dokter;

    @Column(name = "tanngal_pemeriksaan")
    private Date tanggalPemeriksaan;

    private String diagnosa;

    @Column(name = "catatan_medis")
    private String catatanMedis;

}
