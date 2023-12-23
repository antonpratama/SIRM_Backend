package com.anton.sirm.service;

import com.anton.sirm.entity.Dokter;
import com.anton.sirm.entity.Pasien;
import com.anton.sirm.entity.RekamMedis;
import com.anton.sirm.entity.ResepObat;
import com.anton.sirm.model.CreateRekamMedisRequest;
import com.anton.sirm.model.RekamMedisResponse;
import com.anton.sirm.model.UpdateRekamMedisRequest;
import com.anton.sirm.repository.PasienRepository;
import com.anton.sirm.repository.RekamMedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RekamMedisService {

    @Autowired
    private RekamMedisRepository rekamMedisRepository;

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private ValidationService validationService;

    private RekamMedisResponse toRekamMedisResponse(RekamMedis rekamMedis){
        return RekamMedisResponse.builder()
                .idRekamMedis(rekamMedis.getIdRekamMedis())
                .tanggalPemeriksaan(rekamMedis.getTanggalPemeriksaan())
                .diagnosa(rekamMedis.getDiagnosa())
                .catatanMedis(rekamMedis.getCatatanMedis())
                .build();
    }

    @Transactional
    public RekamMedisResponse create(Dokter dokter, Pasien pasien,
                                     ResepObat resepObat, CreateRekamMedisRequest request){
        validationService.validate(request);

        RekamMedis rekamMedis = new RekamMedis();
        rekamMedis.setDokter(dokter);
        rekamMedis.setPasien(pasien);
        rekamMedis.setResepObat(resepObat);
        rekamMedis.setTanggalPemeriksaan(request.getTanggalPemeriksaan());
        rekamMedis.setDiagnosa(request.getDiagnosa());
        rekamMedis.setCatatanMedis(request.getCatatanMedis());

        rekamMedisRepository.save(rekamMedis);

        return toRekamMedisResponse(rekamMedis);
    }

    @Transactional(readOnly = true)
    public RekamMedisResponse get(String idRekamMedis){
        RekamMedis rekamMedis = rekamMedisRepository.findById(idRekamMedis)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rekam medis not found"));

        return toRekamMedisResponse(rekamMedis);
    }

    public RekamMedisResponse update(Dokter dokter, Pasien pasien,
                                     ResepObat resepObat, UpdateRekamMedisRequest request){
        validationService.validate(request);

        RekamMedis rekamMedis = rekamMedisRepository.findById(request.getIdRekamMedis())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rekam medis not found"));

        rekamMedis.setDokter(dokter);
        rekamMedis.setPasien(pasien);
        rekamMedis.setResepObat(resepObat);
        rekamMedis.setTanggalPemeriksaan(request.getTanggalPemeriksaan());
        rekamMedis.setDiagnosa(request.getDiagnosa());
        rekamMedis.setCatatanMedis(rekamMedis.getCatatanMedis());
        rekamMedisRepository.save(rekamMedis);

        return toRekamMedisResponse(rekamMedis);
    }

    @Transactional
    public void delete(String idRekamMedis){
        RekamMedis rekamMedis = rekamMedisRepository.findById(idRekamMedis)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rekam medis not found"));

        rekamMedisRepository.delete(rekamMedis);
    }

    @Transactional(readOnly = true)
    public List<RekamMedisResponse> list(String idPasien){
        Pasien pasien = pasienRepository.findById(idPasien)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien not found"));

        List<RekamMedis> listRekamMedis = rekamMedisRepository.findAllByPasien(pasien);

        return listRekamMedis.stream().map(rekamMedis -> toRekamMedisResponse(rekamMedis)).toList();
    }
}
