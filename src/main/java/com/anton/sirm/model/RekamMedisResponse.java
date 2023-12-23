package com.anton.sirm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RekamMedisResponse {

    private String idRekamMedis;

    private Date tanggalPemeriksaan;

    private String diagnosa;

    private String catatanMedis;
}
