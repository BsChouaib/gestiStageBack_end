package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.converter.TeacherDtoConverter;
import com.MCBS.GestiStage.dtos.request.TeacherDto;
import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.AppRole;
import com.MCBS.GestiStage.models.Teacher;
import com.MCBS.GestiStage.repository.AppRoleRepository;
import com.MCBS.GestiStage.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherDtoConverter teacherDtoConverter;
    private final PasswordEncoder passwordEncoder;

    private  final AppRoleRepository appRoleRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherDtoConverter teacherDtoConverter, PasswordEncoder passwordEncoder, AppRoleRepository appRoleRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherDtoConverter = teacherDtoConverter;
        this.passwordEncoder = passwordEncoder;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public List<TeacherDtoResponse> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDtoResponse> teacherDtoResponses = teachers.stream()
                .map(teacher-> teacherDtoConverter.convertToDto(teacher))
                .collect(Collectors.toList());
        return teacherDtoResponses;
    }

    @Override
    public TeacherDtoResponse getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null)
        {
            throw new ApiRequestException("Teacher dose not exist in DB!!!");
        }
        return teacherDtoConverter.convertToDto(teacher);
    }

    @Override
    public void createTeacher(TeacherDto teacherDto) {
        if (teacherRepository.findTeacherByEmail(teacherDto.getEmail())!=null)
        {
            throw new ApiRequestException("Email address is taken!");
        }
        if(teacherDto.getPassword()!= null)
        {
            if(!teacherDto.getPassword().equalsIgnoreCase(teacherDto.getConfirmPassword()))
            {
                throw new ApiRequestException("password not match");
            }
        }
        Teacher teacher = teacherDtoConverter.convertDtoToTeacher(teacherDto);
        // encoding password
        teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        //
        AppRole appRole=appRoleRepository.findByRoleName("TEACHER");
        teacher.getAppRoles().add(appRole);
        teacherRepository.save(teacher);
    }

    // wording IMPL
    @Override
    public void updateTeacher(TeacherDto teacherDto, Long id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null)
        {
            throw new ApiRequestException("Teacher dose not exist in DB!!!");
        }
        if(teacherDto.getPassword()!= null)
        {
            if(!teacherDto.getPassword().equalsIgnoreCase(teacherDto.getConfirmPassword()))
            {
                throw new ApiRequestException("password not match");
            }
        }
        // firstname
        if(teacherDto.getFirstname()!=null)
        {teacher.setFirstname(teacherDto.getFirstname());}
        // lastname
        if(teacherDto.getLastname()!=null)
        {teacher.setLastname(teacherDto.getLastname());}
        // email
        if(teacherDto.getEmail()!=null)
        {teacher.setEmail(teacherDto.getEmail());}
        // password
        if(teacherDto.getPassword()!=null)
        {teacher.setPassword(teacherDto.getPassword());}
        // dateofbirth
        if(teacherDto.getDateofbirth()!=null)
        {teacher.setDateofbirth(teacherDto.getDateofbirth());}
        // phonenumber
        if(teacherDto.getPhonenumber()!=null)
        {teacher.setPhonenumber(teacherDto.getPhonenumber());}
        // postaladdress
        if(teacherDto.getPostaladdress()!=null)
        {teacher.setPostaladdress(teacherDto.getPostaladdress());}
        // country
        if(teacherDto.getCountry()!=null)
        {teacher.setCountry(teacherDto.getCountry());}
        // city
        if(teacherDto.getCity()!=null)
        {teacher.setCity(teacherDto.getCity());}
        // postalcode
        if(teacherDto.getPostalcode()!=null)
        {teacher.setPostalcode(teacherDto.getPostalcode());}
        // gender
        if(teacherDto.getGender()!=null)
        {teacher.setGender(teacherDto.getGender());}
        // Nationality
        if(teacherDto.getNationality()!=null)
        {teacher.setNationality(teacherDto.getNationality());}
        // subjectTaught
        if(teacherDto.getSubjectTaught()!=null)
        {teacher.setSubjectTaught(teacherDto.getSubjectTaught());}
        // teachingLevel
        if(teacherDto.getTeachingLevel()!=null)
        {teacher.setTeachingLevel(teacherDto.getTeachingLevel());}
        // experience
        if(teacherDto.getExperience()!=null)
        {teacher.setExperience(teacherDto.getExperience());}

        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findTeacherById(id);

        System.out.println(teacher);

        if (teacher == null)
        {
            throw new ApiRequestException("Teacher dose not exist in DB!!!");
        }
        teacherRepository.delete(teacher);

    }
}
