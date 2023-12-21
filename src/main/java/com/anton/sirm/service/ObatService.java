package com.anton.sirm.service;

import com.anton.sirm.entity.Obat;
import com.anton.sirm.model.*;
import com.anton.sirm.repository.ObatRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.val;
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
public class ObatService {

    @Autowired
    private ObatRepository obatRepository;

    @Autowired
    private ValidationService validationService;

    private ObatResponse toObatResponse(Obat obat){
        return ObatResponse.builder()
                .idObat(obat.getIdObat())
                .namaObat(obat.getNamaObat())
                .deskripsi(obat.getDeskripsi())
                .dosis(obat.getDosis())
                .build();
    }

    public ObatResponse create(CreateObatRequest request){
        validationService.validate(request);

        Obat obat = new Obat();
        obat.setNamaObat(request.getNamaObat());
        obat.setDeskripsi(request.getDeskripsi());
        obat.setDosis(request.getDosis());

        obatRepository.save(obat);

        return toObatResponse(obat);
    }

    @Transactional(readOnly = true)
    public ObatResponse get(String idObat){
        Obat obat = obatRepository.findById(idObat)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Obat not found"));

        return toObatResponse(obat);
    }

    public ObatResponse update(UpdateObatRequest request){
        validationService.validate(request);

        Obat obat = obatRepository.findById(request.getIdObat())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Obat not found"));

        obat.setNamaObat(request.getNamaObat());
        obat.setDeskripsi(request.getDeskripsi());
        obat.setDosis(request.getDosis());

        obatRepository.save(obat);

        return toObatResponse(obat);
    }

    @Transactional()
    public void delete(String idObat){
        Obat obat = obatRepository.findById(idObat)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Obat not found"));

        obatRepository.delete(obat);
    }

    public Page<ObatResponse> search(SearchObatRequest request){
        Specification<Obat> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(request.getNamaObat())){
                predicates.add(builder.like(root.get("namaObat"), "%"+request.getNamaObat()+"%"));
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Obat> obats = obatRepository.findAll(specification, pageable);
        List<ObatResponse> obatResponses = obats.getContent().stream()
                .map(obat -> toObatResponse(obat))
                .collect(Collectors.toList());

        return new PageImpl<>(obatResponses, pageable, obats.getTotalElements());
    }
}
