package com.anton.sirm.controller;

import com.anton.sirm.entity.Obat;
import com.anton.sirm.entity.RekamMedis;
import com.anton.sirm.model.ResepObatResponse;
import com.anton.sirm.model.UpdateResepObatRequest;
import com.anton.sirm.model.WebResponse;
import com.anton.sirm.service.ResepObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResepObatController {

    @Autowired
    private ResepObatService resepObatService;


    @GetMapping(
            path = "/api/resepobat/{idResepObat}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ResepObatResponse> get(@PathVariable("idResepObat") String idResepObat){
        ResepObatResponse resepObatResponse = resepObatService.get(idResepObat);
        return WebResponse.<ResepObatResponse>builder().data(resepObatResponse).build();
    }

    @PutMapping(
            path = "/api/resepobat/update/{idResepObat}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ResepObatResponse> update(Obat obat,
                                                 RekamMedis rekamMedis,
                                                 @RequestBody UpdateResepObatRequest request,
                                                 @PathVariable("idResepObat") String idResepObat){

        request.setIdResepObat(idResepObat);

        ResepObatResponse resepObatResponse = resepObatService.update(obat, rekamMedis, request);

        return WebResponse.<ResepObatResponse>builder().data(resepObatResponse).build();
    }

    @DeleteMapping(
            path = "/api/obat/delete/{idResepObat}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("idResepObat") String idResepObat){
        resepObatService.delete(idResepObat);

        return WebResponse.<String>builder().data("OK").build();
    }
}
