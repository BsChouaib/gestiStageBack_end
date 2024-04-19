package com.MCBS.GestiStage.dtos.response;

public record RegisterResponseDTO(  String firstname,
                                    String lastname,
                                    String email,
                                    Boolean isActive,
                                    String role  ){

}
