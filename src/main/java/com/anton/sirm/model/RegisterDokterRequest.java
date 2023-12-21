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
public class RegisterDokterRequest {

    @NotBlank
    @Size(max = 100)
    private String idDokter;

    @NotBlank
    @Size(max = 100)
    private String namaDokter;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String spesialisasi;

    @NotBlank
    @Size(max = 100)
    private String nomorLisensi;
}
