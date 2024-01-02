package com.anton.sirm.controller;

import com.anton.sirm.model.*;
import com.anton.sirm.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
public class ObatController {

    @Autowired
    private ObatService obatService;

    @PostMapping(
            path = "/api/obat/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ObatResponse> create(@RequestBody CreateObatRequest request){
        ObatResponse obatResponse = obatService.create(request);
        return WebResponse.<ObatResponse>builder().data(obatResponse).build();
    }

    @GetMapping(
            path = "/api/obat/{idObat}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ObatResponse> get(@PathVariable("idObat") String idObat){
        ObatResponse obatResponse = obatService.get(idObat);
        return WebResponse.<ObatResponse>builder().data(obatResponse).build();
    }

    @PutMapping(
            path = "/api/obat/update/{idObat}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ObatResponse> update(@RequestBody UpdateObatRequest request,
                                            @PathVariable("idObat") String idObat){
        request.setIdObat(idObat);

        ObatResponse obatResponse = obatService.update(request);
        return WebResponse.<ObatResponse>builder().data(obatResponse).build();
    }

    @DeleteMapping(
            path = "/api/obat/delete/{idObat}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("idObat") String idObat){
        obatService.delete(idObat);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/obat/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ObatResponse>> search(@RequestParam(value = "nameObat", required = false) String namaObat,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size){
        SearchObatRequest request = SearchObatRequest.builder()
                .page(page)
                .size(size)
                .namaObat(namaObat)
                .build();

        Page<ObatResponse> obatResponses = obatService.search(request);
        return WebResponse.<List<ObatResponse>>builder()
                .data(obatResponses.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(obatResponses.getNumber())
                        .totalPage(obatResponses.getTotalPages())
                        .size(obatResponses.getSize())
                        .build())
                .build();
    }
}
