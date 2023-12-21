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
@Table(name = "resep_obat")
public class ResepObat extends BaseEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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

    @OneToMany(mappedBy = "resep_obat")
    private List<RekamMedis> rekamMedis;
}
