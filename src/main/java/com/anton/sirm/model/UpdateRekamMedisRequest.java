package com.anton.sirm.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRekamMedisRequest {

    @NotBlank
    private String idRekamMedis;

    @NotBlank
    private Date tanggalPemeriksaan;

    @NotBlank
    @Size(max = 100)
    private String diagnosa;

    @NotBlank
    @Size(max = 100)
    private String catatanMedis;
}
