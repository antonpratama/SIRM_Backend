package com.anton.sirm.controller;

import com.anton.sirm.entity.Dokter;
import com.anton.sirm.entity.Pasien;
import com.anton.sirm.entity.ResepObat;
import com.anton.sirm.model.CreateRekamMedisRequest;
import com.anton.sirm.model.RekamMedisResponse;
import com.anton.sirm.model.UpdateRekamMedisRequest;
import com.anton.sirm.model.WebResponse;
import com.anton.sirm.service.RekamMedisService;
import com.anton.sirm.service.ResepObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RekamMedisController {

    @Autowired
    private RekamMedisService rekamMedisService;

    @Autowired
    private ResepObatService resepObatService;

    @PostMapping(
            path = "/api/rekammedis/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RekamMedisResponse> create(Dokter dokter, Pasien pasien,
                                                  @RequestBody CreateRekamMedisRequest request){
        RekamMedisResponse rekamMedisResponse = rekamMedisService.create(dokter, pasien, request);
        return WebResponse.<RekamMedisResponse>builder().data(rekamMedisResponse).build();
    }

    @GetMapping(
            path = "/api/rekammedis/{idRekamMedis}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RekamMedisResponse> get(@PathVariable("idRekamMedis") String idRekamMedis){

        RekamMedisResponse rekamMedisResponse = rekamMedisService.get(idRekamMedis);
        return WebResponse.<RekamMedisResponse>builder().data(rekamMedisResponse).build();
    }

    @PutMapping(
            path = "/api/rekammedis/update/{idRekamMedis}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RekamMedisResponse> update(Dokter dokter, Pasien pasien,
                                                  @RequestBody UpdateRekamMedisRequest request,
                                                  @PathVariable("idRekamMedis") String idRekamMedis){
        request.setIdRekamMedis(idRekamMedis);

        RekamMedisResponse rekamMedisResponse = rekamMedisService.update(dokter, pasien, request);
        return WebResponse.<RekamMedisResponse>builder().data(rekamMedisResponse).build();
    }

    @DeleteMapping(
            path = "/api/rekammedis/delete/{idRekamMedis}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("idRekamMedis") String idRekamMedis){
        rekamMedisService.delete(idRekamMedis);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/pasien/{idPasien}/rekammedis"
    )
    public WebResponse<List<RekamMedisResponse>> getByPasien(@PathVariable("idPasien") String idPasien){
        List<RekamMedisResponse> rekamMedisResponses = rekamMedisService.list(idPasien);

        return WebResponse.<List<RekamMedisResponse>>builder().data(rekamMedisResponses).build();
    }
}
