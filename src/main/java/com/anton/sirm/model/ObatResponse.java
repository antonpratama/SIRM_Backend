package com.anton.sirm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObatResponse {

    private String idObat;

    private String namaObat;

    private String deskripsi;

    private String dosis;
}
