package com.anton.sirm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResepObatResponse {

    private String idResepObat;

    private int jumlahObat;

    private String instruksiPenggunaan;
}
