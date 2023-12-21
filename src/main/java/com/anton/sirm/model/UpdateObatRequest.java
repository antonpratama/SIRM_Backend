package com.anton.sirm.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateObatRequest {

    @NotBlank
    @Size(max = 100)
    private String idObat;

    @NotBlank
    @Size(max = 100)
    private String namaObat;

    @NotBlank
    @Size(max = 100)
    private String deskripsi;

    @NotBlank
    @Size(max = 100)
    private String dosis;
}
