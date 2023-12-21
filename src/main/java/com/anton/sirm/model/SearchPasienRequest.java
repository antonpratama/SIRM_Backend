package com.anton.sirm.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchPasienRequest {

    private String namaPasien;

    private String nomorKontak;

    private String alamat;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
