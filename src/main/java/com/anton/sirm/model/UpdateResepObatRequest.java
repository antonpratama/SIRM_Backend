package com.anton.sirm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResepObatRequest {

    @JsonIgnore
    @NotBlank
    private String idResepObat;

    @NotBlank
    @Size(max = 100)
    private int jumlahObat;

    @NotBlank
    @Size(max = 100)
    private String instruksiPenggunaan;
}
