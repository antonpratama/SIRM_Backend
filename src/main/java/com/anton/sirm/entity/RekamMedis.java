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
@Table(name = "rekam_medis")
public class RekamMedis extends BaseEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_rekam_medis")
    private String idRekamMedis;

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

    @OneToMany(mappedBy = "rekamMedis")
    private List<ResepObat> resepObat;

}
