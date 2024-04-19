package com.MCBS.GestiStage.dtos.response;

public record AuthResponseDTO(String accessToken,
                              String refreshToken,
                              String email,
                              String role) {
}
