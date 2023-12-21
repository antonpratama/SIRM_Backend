package com.anton.sirm.service;

import com.anton.sirm.entity.Dokter;
import com.anton.sirm.model.LoginDokterRequest;
import com.anton.sirm.model.TokenResponse;
import com.anton.sirm.repository.DokterRepository;
import com.anton.sirm.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private DokterRepository dokterRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginDokterRequest request){
        validationService.validate(request);

        Dokter dokter = dokterRepository.findById(request.getIdDokter())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "ID Dokter or Password wrong"));

        if (BCrypt.checkpw(request.getPassword(), dokter.getPassword())) {
            dokter.setToken(UUID.randomUUID().toString());
            dokter.setTokenExpiredAt(next30Days());

            dokterRepository.save(dokter);

            return TokenResponse.builder()
                    .token(dokter.getToken())
                    .expiredAt(dokter.getTokenExpiredAt())
                    .build();
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }

    @Transactional
    public void logout(Dokter dokter){
        dokter.setToken(null);
        dokter.setTokenExpiredAt(null);

        dokterRepository.save(dokter);
    }
}
