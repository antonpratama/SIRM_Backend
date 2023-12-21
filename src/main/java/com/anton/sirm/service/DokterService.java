package com.anton.sirm.service;

import com.anton.sirm.entity.Dokter;
import com.anton.sirm.model.DokterResponse;
import com.anton.sirm.model.RegisterDokterRequest;
import com.anton.sirm.model.UpdateDokterRequest;
import com.anton.sirm.repository.DokterRepository;
import com.anton.sirm.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Timestamp;
import java.util.Date;
import java.util.Objects;

@Service
public class DokterService {

    @Autowired
    private DokterRepository dokterRepository;

    @Autowired
    private ValidationService validationService;


    public void register(RegisterDokterRequest request){

        validationService.validate(request);

        if (dokterRepository.existsById(request.getIdDokter())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Dokter Already Registered");
        }

        Dokter dokter = new Dokter();
        dokter.setIdDokter(request.getIdDokter());
        dokter.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        dokter.setNamaDokter(request.getNamaDokter());
        dokter.setSpesialisasi(request.getSpesialisasi());
        dokter.setNomorLisensi(request.getNomorLisensi());

        dokterRepository.save(dokter);
    }

    public DokterResponse get(Dokter dokter){
        return DokterResponse.builder()
                .namaDokter(dokter.getNamaDokter())
                .idDokter(dokter.getIdDokter())
                .build();
    }

    @Transactional
    public DokterResponse update(Dokter dokter, UpdateDokterRequest request){
        validationService.validate(request);

        if(Objects.nonNull(request.getNamaDokter())) {
            dokter.setNamaDokter(request.getNamaDokter());
        }

        if(Objects.nonNull(request.getSpesialisasi())) {
            dokter.setSpesialisasi(request.getSpesialisasi());
        }

        if(Objects.nonNull(request.getNomorLisensi())) {
            dokter.setNomorLisensi(request.getNomorLisensi());
        }

        if(Objects.nonNull(request.getPassword())) {
            dokter.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        dokterRepository.save(dokter);

        return DokterResponse.builder()
                .namaDokter(dokter.getNamaDokter())
                .idDokter(dokter.getIdDokter())
                .build();
    }
}
