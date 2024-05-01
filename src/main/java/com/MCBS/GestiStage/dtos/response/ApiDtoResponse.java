package com.MCBS.GestiStage.dtos.response;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public record ApiDtoResponse ( String message,
                              List<?> data ) {
}
