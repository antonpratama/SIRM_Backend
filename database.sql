USE sirm;

CREATE TABLE dokter (
    id_dokter VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL ,
    nama_dokter VARCHAR(100) NOT NULL ,
    spesialisasi VARCHAR(100) NOT NULL ,
    nomor_lisensi VARCHAR(100) NOT NULL ,
    token VARCHAR(100),
    token_expired_at BIGINT,
    created_time DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_time DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_dokter),
    UNIQUE (token)
) ENGINE InnoDB;

CREATE TABLE pasien (
    id_pasien VARCHAR(100) NOT NULL ,
    nama_pasien VARCHAR(100) NOT NULL ,
    alamat VARCHAR(100) NOT NULL ,
    tanggal_lahir DATE NOT NULL ,
    jenis_kelamin VARCHAR(10) NOT NULL,
    nomor_kontak VARCHAR(20),
    foto_pasien BLOB,
    created_time DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_time DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_pasien)
) ENGINE InnoDB;

CREATE TABLE obat (
    id_obat VARCHAR(100) NOT NULL ,
    nama_obat VARCHAR(100) NOT NULL ,
    deskripsi VARCHAR(100) NOT NULL ,
    dosis VARCHAR(100) NOT NULL ,
    created_time DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_time DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_obat)
) ENGINE InnoDB;

CREATE TABLE resep_obat (
    id_resep_obat VARCHAR(100) NOT NULL ,
    id_obat VARCHAR(100) NOT NULL ,
    id_rekam_medis VARCHAR(100) NOT NULL ,
    jumlah_obat int NOT NULL ,
    instruksi_penggunaan VARCHAR(100) NOT NULL ,
    created_time DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_time DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_resep_obat),
    FOREIGN KEY fk_id_obat (id_obat) REFERENCES obat(id_obat),
    FOREIGN KEY fk_id_rekam_medis (id_rekam_medis) REFERENCES rekam_medis(id_rekam_medis)
) ENGINE InnoDB;

CREATE TABLE rekam_medis (
    id_rekam_medis VARCHAR(100) NOT NULL ,
    id_pasien VARCHAR(100) NOT NULL ,
    id_dokter VARCHAR(100) NOT NULL ,
    tanggal_pemeriksaan DATE NOT NULL ,
    diagnosa VARCHAR(100) NOT NULL ,
    catatan_medis VARCHAR(100) ,
    created_time DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_time DATETIME NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_rekam_medis),
    FOREIGN KEY fk_id_pasien (id_pasien) REFERENCES pasien(id_pasien),
    FOREIGN KEY fk_id_dokter (id_dokter) REFERENCES dokter(id_dokter)
) ENGINE InnoDB;