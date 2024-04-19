package com.MCBS.GestiStage.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto extends AppUserDto {

    private String accountcreationdate;
    private String accesslevel;
}
