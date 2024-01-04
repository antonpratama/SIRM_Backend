package com.anton.sirm.service;

import com.anton.sirm.entity.Obat;
import com.anton.sirm.entity.RekamMedis;
import com.anton.sirm.entity.ResepObat;
import com.anton.sirm.model.CreateResepObatRequest;
import com.anton.sirm.model.ResepObatResponse;
import com.anton.sirm.model.UpdateResepObatRequest;
import com.anton.sirm.repository.ResepObatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ResepObatService {

    @Autowired
    private ResepObatRepository resepObatRepository;

    @Autowired
    private ValidationService validationService;

    private ResepObatResponse toResepObatResponse(ResepObat resepObat){
        return ResepObatResponse.builder()
                .idResepObat(resepObat.getIdResepObat())
                .jumlahObat(resepObat.getJumlahObat())
                .instruksiPenggunaan(resepObat.getInstruksiPenggunaan())
                .build();
    }

    @Transactional
    public ResepObatResponse create(Obat obat, RekamMedis rekamMedis, CreateResepObatRequest request){
        validationService.validate(request);

        ResepObat resepObat = new ResepObat();
        resepObat.setObat(obat);
        resepObat.setRekamMedis(rekamMedis);
        resepObat.setJumlahObat(request.getJumlahObat());
        resepObat.setInstruksiPenggunaan(request.getInstruksiPenggunaan());

        resepObatRepository.save(resepObat);

        return toResepObatResponse(resepObat);
    }

    @Transactional(readOnly = true)
    public ResepObatResponse get(String idResepObat){
        ResepObat resepObat = resepObatRepository.findById(idResepObat)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resep Obat not found"));

        return toResepObatResponse(resepObat);
    }

    public ResepObatResponse update(Obat obat, RekamMedis rekamMedis, UpdateResepObatRequest request){
        validationService.validate(request);

        ResepObat resepObat = resepObatRepository.findById(request.getIdResepObat())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resep Obat not found"));

        resepObat.setJumlahObat(resepObat.getJumlahObat());
        resepObat.setInstruksiPenggunaan(resepObat.getInstruksiPenggunaan());
        resepObat.setObat(obat);
        resepObat.setRekamMedis(rekamMedis);
        resepObatRepository.save(resepObat);

        return toResepObatResponse(resepObat);
    }

    @Transactional()
    public void delete(String isResepObat){
        ResepObat resepObat = resepObatRepository.findById(isResepObat)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resep Obat not found"));

        resepObatRepository.delete(resepObat);
    }
}
