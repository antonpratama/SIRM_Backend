package com.anton.sirm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rekam_medis")
public class RekamMedis {

    @Id
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

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
