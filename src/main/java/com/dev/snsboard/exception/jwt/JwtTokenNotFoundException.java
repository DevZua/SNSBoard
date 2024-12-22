package com.dev.snsboard.exception.jwt;

import io.jsonwebtoken.JwtException;

public class JwtTokenNotFoundException extends JwtException {

    public JwtTokenNotFoundException() {
        super("JWT not found.");
    }
}