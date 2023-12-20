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
@Table(name = "obat")
public class Obat {

    @Id
    @Column(name = "id_obat")
    private String idObat;

    @Column(name = "nama_obat")
    private String namaObat;

    private String deskripsi;

    private String dosis;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "obat")
    private List<ResepObat> resepObat;
}
