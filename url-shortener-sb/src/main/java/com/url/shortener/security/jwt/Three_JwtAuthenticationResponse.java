package com.url.shortener.security.jwt;


public class Three_JwtAuthenticationResponse { /*Este clase es una manera de
    como el servidor va a enviar la respuesta de autenticacion al usuario */

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Three_JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
