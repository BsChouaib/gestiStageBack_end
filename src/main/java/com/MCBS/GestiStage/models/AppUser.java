package com.MCBS.GestiStage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    @Column(name = "email", unique = true)
    private String email;
    private Date dateofbirth;
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
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Claim> Claims = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(Long id, String firstname, String lastname, String password, String email, Date dateofbirth, String phonenumber, String postaladdress, String country, String city, String postalcode, String gender, String nationality, Boolean isActive, List<AppRole> appRoles, List<Claim> claims) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.dateofbirth = dateofbirth;
        this.phonenumber = phonenumber;
        this.postaladdress = postaladdress;
        this.country = country;
        this.city = city;
        this.postalcode = postalcode;
        this.gender = gender;
        Nationality = nationality;
        this.isActive = isActive;
        this.appRoles = appRoles;
        Claims = claims;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPostaladdress() {
        return postaladdress;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return Nationality;
    }

    public Boolean getActive() {
        return isActive;
    }

    public List<AppRole> getAppRoles() {
        return appRoles;
    }

    public List<Claim> getClaims() {
        return Claims;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setAppRoles(List<AppRole> appRoles) {
        this.appRoles = appRoles;
    }

    public void setClaims(List<Claim> claims) {
        Claims = claims;
    }
}
