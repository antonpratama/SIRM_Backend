package com.anton.sirm.repository;

import com.anton.sirm.entity.Pasien;
import com.anton.sirm.entity.RekamMedis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RekamMedisRepository extends JpaRepository<RekamMedis, String> {

    List<RekamMedis> findAllByPasien(Pasien pasien);
}
