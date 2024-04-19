package com.MCBS.GestiStage.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto{

    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Long dateofbirth;
    private String phonenumber;
    private String postaladdress;
    private String country;
    private String city;
    private String postalcode;
    private String gender;
    private Boolean isActive;
    private String Nationality;
    private String role;
}
