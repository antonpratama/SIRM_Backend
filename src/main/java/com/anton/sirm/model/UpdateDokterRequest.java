package com.anton.sirm.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDokterRequest {

    @Size(max = 100)
    private String namaDokter;

    @Size(max = 100)
    private String spesialisasi;

    @Size(max = 100)
    private String password;

    @Size(max = 100)
    private String nomorLisensi;
}
