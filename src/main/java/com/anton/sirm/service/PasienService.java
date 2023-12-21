package com.anton.sirm.service;

import com.anton.sirm.entity.Pasien;
import com.anton.sirm.model.CreatePasienRequest;
import com.anton.sirm.model.PasienResponse;
import com.anton.sirm.model.SearchPasienRequest;
import com.anton.sirm.model.UpdatePasienRequest;
import com.anton.sirm.repository.PasienRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PasienService {

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public PasienResponse create(CreatePasienRequest request){
        validationService.validate(request);

        Pasien pasien = new Pasien();
        pasien.setNamaPasien(request.getNamaPasien());
        pasien.setAlamat(request.getAlamat());
        pasien.setNomorKontak(request.getNomorKontak());
        pasien.setJenisKelamin(request.getJenisKelamin());
        pasien.setTanggalLahir(request.getTanggalLahir());
        pasien.setFotoPasien(request.getFotoPasien());

        pasienRepository.save(pasien);

        return toPasienResponse(pasien);

    }

    private PasienResponse toPasienResponse(Pasien pasien){
        return PasienResponse.builder()
                .namaPasien(pasien.getNamaPasien())
                .alamat(pasien.getAlamat())
                .jenisKelamin(pasien.getJenisKelamin())
                .nomorKontak(pasien.getNomorKontak())
                .tanggalLahir(pasien.getTanggalLahir())
                .fotoPasien(pasien.getFotoPasien())
                .build();
    }

    @Transactional(readOnly = true)
    public PasienResponse get(String idPasien){
        Pasien pasien = pasienRepository.findById(idPasien)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien not found"));

        return toPasienResponse(pasien);
    }

    public PasienResponse update(UpdatePasienRequest request){
        validationService.validate(request);

        Pasien pasien = pasienRepository.findById(request.getIdPasien())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien not found"));

        pasien.setNamaPasien(request.getNamaPasien());
        pasien.setTanggalLahir(request.getTanggalLahir());
        pasien.setAlamat(request.getAlamat());
        pasien.setNomorKontak(request.getNomorKontak());
        pasien.setJenisKelamin(request.getJenisKelamin());
        pasien.setFotoPasien(request.getFotoPasien());

        pasienRepository.save(pasien);

        return toPasienResponse(pasien);
    }

    @Transactional()
    public void delete(String idPasien){
        Pasien pasien = pasienRepository.findById(idPasien)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien not found"));

        pasienRepository.delete(pasien);
    }

    @Transactional(readOnly = true)
    public Page<PasienResponse> search(SearchPasienRequest request){
        Specification<Pasien> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(request.getNamaPasien())){
                predicates.add(builder.like(root.get("namaPasien"), "%"+request.getNamaPasien()+"%"));
            }

            if (Objects.nonNull(request.getNomorKontak())){
                predicates.add(builder.like(root.get("nomorKontak"), "%"+request.getNomorKontak()+"%"));
            }

            if (Objects.nonNull(request.getAlamat())){
                predicates.add(builder.like(root.get("alamat"), "%"+request.getAlamat()+"%"));
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Pasien> pasiens = pasienRepository.findAll(specification, pageable);
        List<PasienResponse> pasienResponses = pasiens.getContent().stream()
                .map(pasien -> toPasienResponse(pasien))
                .collect(Collectors.toList());

        return new PageImpl<>(pasienResponses, pageable, pasiens.getTotalElements());
    }
}
