package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.AppUserDto;
import com.MCBS.GestiStage.models.AppRole;
import com.MCBS.GestiStage.models.AppUser;


public interface AccountService {
    AppUserDto createNewUser(AppUserDto appUserDto);


    /*AppUserDto getUserById(int userId);
    AppUserDto updateUser(int userId, AppUserDto reviewDto);
    void deleteUser(int userId);*/
    AppUser getUserByEmail(String email);
    public AppRole newRole(AppRole appRole);
    //void addRoleToUser(String email,String roleName);
}
