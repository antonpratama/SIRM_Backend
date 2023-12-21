package com.anton.sirm.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDokterRequest {

    @NotBlank
    @Size(max = 100)
    private String idDokter;

    @NotBlank
    @Size(max = 100)
    private String password;
}
