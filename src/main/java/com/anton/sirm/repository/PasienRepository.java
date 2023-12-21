package com.anton.sirm.repository;

import com.anton.sirm.entity.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PasienRepository extends JpaRepository<Pasien, String>, JpaSpecificationExecutor<Pasien> {
}
