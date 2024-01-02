package com.anton.sirm.controller;

import com.anton.sirm.entity.Dokter;
import com.anton.sirm.model.DokterResponse;
import com.anton.sirm.model.RegisterDokterRequest;
import com.anton.sirm.model.UpdateDokterRequest;
import com.anton.sirm.model.WebResponse;
import com.anton.sirm.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DokterController {

    @Autowired
    private DokterService dokterService;

    @PostMapping(
            path = "/api/dokter/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterDokterRequest request){
        dokterService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "api/dokter/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<DokterResponse> get(Dokter dokter){
        DokterResponse dokterResponse = dokterService.get(dokter);
        return WebResponse.<DokterResponse>builder().data(dokterResponse).build();
    }

    @PatchMapping(
            path = "api/dokter/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<DokterResponse> update(Dokter dokter, @RequestBody UpdateDokterRequest request){
        DokterResponse dokterResponse = dokterService.update(dokter, request);
        return WebResponse.<DokterResponse>builder().data(dokterResponse).build();
    }
}
