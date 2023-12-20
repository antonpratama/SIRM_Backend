package com.anton.sirm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resep_obat")
public class ResepObat {

    @Id
    @Column(name = "id_resep_obat")
    private String idResepObat;

    @ManyToOne
    @JoinColumn (name = "id_obat", referencedColumnName = "id_obat")
    private Obat obat;

    @Column(name = "id_rekam_medis")
    private String idRekamMedis;

    @Column(name = "jumlah_obat")
    private int jumlahObat;

    @Column(name = "instruksi_penggunaan")
    private String instruksiPenggunaan;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "resep_obat")
    private List<RekamMedis> rekamMedis;
}
