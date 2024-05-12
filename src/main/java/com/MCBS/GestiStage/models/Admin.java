package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Admin extends AppUser{
    private String accountcreationdate;
    private String accesslevel;

    public Admin() {
    }


    public Admin(Long id, String firstname, String lastname, String password, String email, Date dateofbirth, String phonenumber, String postaladdress, String country, String city, String postalcode, String gender, String nationality, Boolean isActive, List<AppRole> appRoles, List<Claim> claims, String accountcreationdate, String accesslevel) {
        super(id, firstname, lastname, password, email, dateofbirth, phonenumber, postaladdress, country, city, postalcode, gender, nationality, isActive, appRoles, claims);
        this.accountcreationdate = accountcreationdate;
        this.accesslevel = accesslevel;
    }

    public String getAccountcreationdate() {
        return accountcreationdate;
    }

    public String getAccesslevel() {
        return accesslevel;
    }

    public void setAccountcreationdate(String accountcreationdate) {
        this.accountcreationdate = accountcreationdate;
    }

    public void setAccesslevel(String accesslevel) {
        this.accesslevel = accesslevel;
    }
}
