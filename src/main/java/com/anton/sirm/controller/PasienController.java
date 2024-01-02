package com.anton.sirm.controller;

import com.anton.sirm.model.*;
import com.anton.sirm.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @PostMapping(
            path = "/api/pasien/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PasienResponse> create(@RequestBody CreatePasienRequest request){
        PasienResponse pasienResponse = pasienService.create(request);
        return WebResponse.<PasienResponse>builder().data(pasienResponse).build();
    }

    @GetMapping(
            path = "/api/pasien/{idPasien}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PasienResponse> get(@PathVariable("idPasien") String idPasien){
        PasienResponse pasienResponse = pasienService.get(idPasien);
        return WebResponse.<PasienResponse>builder().data(pasienResponse).build();
    }

    @PutMapping(
            path = "/api/pasien/{idPasien}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PasienResponse> update(@RequestBody UpdatePasienRequest request,
                                              @PathVariable("idPasien") String idPasien){
        request.setIdPasien(idPasien);

        PasienResponse pasienResponse = pasienService.update(request);
        return WebResponse.<PasienResponse>builder().data(pasienResponse).build();
    }

    @DeleteMapping(
            path = "/api/pasien/{idPasien}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("idPasien") String idPasien){
        pasienService.delete(idPasien);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/pasien/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<PasienResponse>> search(@RequestParam(value = "nama", required = false) String namaPasien,
                                                    @RequestParam(value = "nomorKontak", required = false) String nomorKontak,
                                                    @RequestParam(value = "alamat", required = false) String alamat,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size){
        SearchPasienRequest request = SearchPasienRequest.builder()
                .page(page)
                .size(size)
                .namaPasien(namaPasien)
                .nomorKontak(nomorKontak)
                .alamat(alamat)
                .build();

        Page<PasienResponse> pasienResponses = pasienService.search(request);
        return WebResponse.<List<PasienResponse>>builder()
                .data(pasienResponses.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(pasienResponses.getNumber())
                        .totalPage(pasienResponses.getTotalPages())
                        .size(pasienResponses.getSize())
                        .build())
                .build();
    }
}
