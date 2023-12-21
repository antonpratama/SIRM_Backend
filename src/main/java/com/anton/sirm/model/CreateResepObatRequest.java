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
public class CreateResepObatRequest {

    @NotBlank
    @Size(max = 100)
    private int jumlahObat;

    @NotBlank
    @Size(max = 100)
    private String instruksiPenggunaan;
}
