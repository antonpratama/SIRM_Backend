package com.anton.sirm.repository;

import com.anton.sirm.entity.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ObatRepository extends JpaRepository<Obat, String>, JpaSpecificationExecutor<Obat> {
}
