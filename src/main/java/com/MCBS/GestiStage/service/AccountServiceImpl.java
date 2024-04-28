package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.converter.StudentDtoConverter;
import com.MCBS.GestiStage.dtos.request.AdminDto;
import com.MCBS.GestiStage.dtos.request.AppUserDto;
import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.request.TeacherDto;
import com.MCBS.GestiStage.exceptions.ApiExceptionHandler;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.*;
import com.MCBS.GestiStage.repository.AppRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.MCBS.GestiStage.repository.AppUserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    private StudentDtoConverter studentDtoConverter;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder, StudentDtoConverter studentDtoConverter) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentDtoConverter = studentDtoConverter;
    }

    @Override
    public AppUserDto createNewUser(AppUserDto appUserDto) {

        if (appUserDto instanceof AdminDto)
        {
            String roleName = appUserDto.getRole();
            Admin appUser = mapAdminToEntity(roleName,(AdminDto)appUserDto);
            // check email address
            // Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
            Admin newAppUser = appUserRepository.save(appUser);
            return mapAdminToDto(newAppUser);
        }
        else if(appUserDto instanceof TeacherDto)
        {
            String roleName = appUserDto.getRole();
            Teacher appUser = mapTeacherToEntity(roleName,(TeacherDto)appUserDto);
            // check email address
            // Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
            Teacher newAppUser = appUserRepository.save(appUser);
            return mapTeacherToDto(newAppUser);
        }
        else if(appUserDto instanceof StudentDto)
        {
            String roleName = appUserDto.getRole();
            Student appUser = mapStudentToEntity(roleName,(StudentDto) appUserDto);
            // check email address
            // Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
            Student newAppUser = appUserRepository.save(appUser);
            return mapStudentToDto(newAppUser);
        }
        else
        {
            // Handle unknown user type
            throw new ApiRequestException("Unknown user type");
        }
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public AppRole newRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }



    private Admin mapAdminToEntity(String roleName,AdminDto adminDto) {
        // get role from db
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        //
        Admin admin = new Admin();
        admin.setFirstname(adminDto.getFirstname());
        admin.setLastname(adminDto.getLastname());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setEmail(adminDto.getEmail());
        admin.setDateofbirth(adminDto.getDateofbirth());
        admin.setPhonenumber(adminDto.getPhonenumber());
        admin.setPostaladdress(adminDto.getPostaladdress());
        admin.setCountry(adminDto.getCountry());
        admin.setCity(adminDto.getCity());
        admin.setPostalcode(adminDto.getPostalcode());
        admin.setGender(adminDto.getGender());
        admin.setIsActive(true);
        admin.getAppRoles().add(appRole);
        admin.setNationality(adminDto.getNationality());
        admin.setAccountcreationdate(adminDto.getAccountcreationdate());
        admin.setAccesslevel(adminDto.getAccesslevel());
        return admin;
    }

    private AppUserDto mapAdminToDto(Admin admin) {
        AppUserDto adminDto = new AppUserDto();
        adminDto.setFirstname(admin.getFirstname());
        adminDto.setLastname(admin.getLastname());
        adminDto.setEmail(admin.getEmail());
        adminDto.setDateofbirth(admin.getDateofbirth());
        adminDto.setPhonenumber(admin.getPhonenumber());
        adminDto.setPostaladdress(admin.getPostaladdress());
        adminDto.setCountry(admin.getCountry());
        adminDto.setCity(admin.getCity());
        adminDto.setPostalcode(admin.getPostalcode());
        adminDto.setGender(admin.getGender());
        adminDto.setRole("ADMIN");
        adminDto.setNationality(admin.getNationality());
        /*adminDto.setAccountcreationdate(admin.getAccountcreationdate());
        adminDto.setAccesslevel(admin.getAccesslevel());*/
        return adminDto;
    }

    //
    private Student mapStudentToEntity(String roleName, StudentDto studentDto) {
        // get role from db
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        //
        Student student = new Student();
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        student.setEmail(studentDto.getEmail());
        student.setDateofbirth(studentDto.getDateofbirth());
        student.setPhonenumber(studentDto.getPhonenumber());
        student.setPostaladdress(studentDto.getPostaladdress());
        student.setCountry(studentDto.getCountry());
        student.setCity(studentDto.getCity());
        student.setPostalcode(studentDto.getPostalcode());
        student.setGender(studentDto.getGender());
        student.setIsActive(false);
        student.getAppRoles().add(appRole);
        student.setNationality(studentDto.getNationality());
        student.setCurrentStudyLevel(studentDto.getCurrentStudyLevel());
        student.setCurrentInstitution(studentDto.getCurrentInstitution());
        student.setStudyField(studentDto.getStudyField());
        student.setEnrollmentYear(studentDto.getEnrollmentYear());
        return student;
    }

    private AppUserDto mapStudentToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstname(student.getFirstname());
        studentDto.setLastname(student.getLastname());
        studentDto.setEmail(student.getEmail());
        studentDto.setDateofbirth(student.getDateofbirth());
        studentDto.setPhonenumber(student.getPhonenumber());
        studentDto.setPostaladdress(student.getPostaladdress());
        studentDto.setCountry(student.getCountry());
        studentDto.setCity(student.getCity());
        studentDto.setPostalcode(student.getPostalcode());
        studentDto.setGender(student.getGender());
        studentDto.setRole("STUDENT");
        studentDto.setNationality(student.getNationality());
        studentDto.setCurrentStudyLevel(student.getCurrentStudyLevel());
        studentDto.setCurrentInstitution(student.getCurrentInstitution());
        studentDto.setStudyField(student.getStudyField());
        studentDto.setEnrollmentYear(student.getEnrollmentYear());
        return studentDto;
    }
    //

    private Teacher mapTeacherToEntity(String roleName, TeacherDto teacherDto) {
        // get role from db
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        //
        Teacher teacher = new Teacher();
        teacher.setFirstname(teacherDto.getFirstname());
        teacher.setLastname(teacherDto.getLastname());
        teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        teacher.setEmail(teacherDto.getEmail());
        teacher.setDateofbirth(teacherDto.getDateofbirth());
        teacher.setPhonenumber(teacherDto.getPhonenumber());
        teacher.setPostaladdress(teacherDto.getPostaladdress());
        teacher.setCountry(teacherDto.getCountry());
        teacher.setCity(teacherDto.getCity());
        teacher.setPostalcode(teacherDto.getPostalcode());
        teacher.setGender(teacherDto.getGender());
        teacher.setIsActive(true);
        teacher.getAppRoles().add(appRole);
        teacher.setNationality(teacherDto.getNationality());
        teacher.setSubjectTaught(teacherDto.getSubjectTaught());
        teacher.setTeachingLevel(teacherDto.getTeachingLevel());
        teacher.setExperience(teacherDto.getExperience());
        return teacher;
    }

    private TeacherDto mapTeacherToDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstname(teacher.getFirstname());
        teacherDto.setLastname(teacher.getLastname());
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setDateofbirth(teacher.getDateofbirth());
        teacherDto.setPhonenumber(teacher.getPhonenumber());
        teacherDto.setPostaladdress(teacher.getPostaladdress());
        teacherDto.setCountry(teacher.getCountry());
        teacherDto.setCity(teacher.getCity());
        teacherDto.setPostalcode(teacher.getPostalcode());
        teacherDto.setGender(teacher.getGender());
        teacherDto.setRole("TEACHER");
        teacherDto.setNationality(teacher.getNationality());
        teacherDto.setSubjectTaught(teacher.getSubjectTaught());
        teacherDto.setTeachingLevel(teacher.getTeachingLevel());
        teacherDto.setExperience(teacher.getExperience());
        return teacherDto;
    }
}
