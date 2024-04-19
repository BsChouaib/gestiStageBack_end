package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    @Column(name = "email", unique = true)
    private String email;
    private Long dateofbirth;
    private String phonenumber;
    private String postaladdress;
    private String country;
    private String city;
    private String postalcode;
    private String gender;
    private String Nationality;
    private Boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> appRoles = new ArrayList<>();
}
