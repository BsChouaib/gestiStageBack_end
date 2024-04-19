package com.MCBS.GestiStage.dtos.request;

public record LoginRequest(
        String grantType,
        String email,
        String password,
        boolean withRefreshToken,
        String refreshToken
) {
}
