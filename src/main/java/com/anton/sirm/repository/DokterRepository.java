package com.anton.sirm.repository;

import com.anton.sirm.entity.Dokter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DokterRepository extends JpaRepository<Dokter, String> {

    Optional<Dokter> findFirstByToken(String token);
}
