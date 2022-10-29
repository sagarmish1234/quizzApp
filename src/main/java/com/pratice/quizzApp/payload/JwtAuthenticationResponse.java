package com.pratice.quizzApp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private boolean master;

    public JwtAuthenticationResponse(String accessToken, boolean isAdmin) {
        this.accessToken = accessToken;
        this.master = isAdmin;
    }
}