package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.StudentDtoConverter;
import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.AppRole;
import com.MCBS.GestiStage.models.Student;
import com.MCBS.GestiStage.repository.AppRoleRepository;
import com.MCBS.GestiStage.repository.StudentRepository;
import com.MCBS.GestiStage.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AppRoleRepository appRoleRepository;

    private  final StudentDtoConverter studentDtoConverter;

    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder, AppRoleRepository appRoleRepository, StudentDtoConverter studentDtoConverter) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.appRoleRepository = appRoleRepository;
        this.studentDtoConverter = studentDtoConverter;
    }

    @Override
    public List<StudentDtoResponse> getAllStudents() {

        List<Student> students = studentRepository.findAll();
        List<StudentDtoResponse> studentDtoResponses = students.stream()
                .map(teacher-> studentDtoConverter.convertToDto(teacher))
                .collect(Collectors.toList());
        return studentDtoResponses;
    }

    @Override
    public StudentDtoResponse getStudentById(Long id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null)
        {
            throw new ApiRequestException("Student dose not exist in DB!!!");
        }
        return studentDtoConverter.convertToDto(student);
    }

    @Override
    public void createStudent(StudentDto studentDto) {
        if (studentRepository.findStudentByEmail(studentDto.getEmail())!=null)
        {
            throw new ApiRequestException("Email address is taken!");
        }
        if(studentDto.getPassword()!= null)
        {
            if(!studentDto.getPassword().equalsIgnoreCase(studentDto.getConfirmPassword()))
            {
                throw new ApiRequestException("password not match");
            }
        }
        Student student = studentDtoConverter.convertDtoToStudent(studentDto);
        // encoding password
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        //
        AppRole appRole=appRoleRepository.findByRoleName("STUDENT");
        student.getAppRoles().add(appRole);
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(StudentDto studentDto, Long id)
    {

        Student student = studentRepository.findStudentById(id);
        if (student == null)
        {
            throw new ApiRequestException("Student dose not exist in DB!!!");
        }
        if(studentDto.getPassword()!= null)
        {
            if(!studentDto.getPassword().equalsIgnoreCase(studentDto.getConfirmPassword()))
            {
                throw new ApiRequestException("password not match");
            }
        }
        // firstname
        if((studentDto.getFirstname()!=null))
        {student.setFirstname(studentDto.getFirstname());}
        // lastname
        if((studentDto.getLastname()!=null))
        {student.setLastname(studentDto.getLastname());}
        // email
        if((studentDto.getEmail()!=null))
        {student.setEmail(studentDto.getEmail());}
        // password
        if((studentDto.getPassword()!=null))
        {student.setPassword(studentDto.getPassword());}
        // dateofbirth
        if(studentDto.getDateofbirth()!=null)
        {student.setDateofbirth(studentDto.getDateofbirth());}
        // phonenumber
        if((studentDto.getPhonenumber()!=null))
        {student.setPhonenumber(studentDto.getPhonenumber());}
        // postaladdress
        if((studentDto.getPostaladdress()!=null))
        {student.setPostaladdress(studentDto.getPostaladdress());}
        // country
        if((studentDto.getCountry()!=null))
        {student.setCountry(studentDto.getCountry());}
        // city
        if((studentDto.getCity()!=null))
        {student.setCity(studentDto.getCity());}
        // postalcode
        if((studentDto.getPostalcode()!=null))
        {student.setPostalcode(studentDto.getPostalcode());}
        // gender
        if((studentDto.getGender()!=null))
        {student.setGender(studentDto.getGender());}
        // Nationality
        if((studentDto.getNationality()!=null))
        {student.setNationality(studentDto.getNationality());}
        // subjectTaught
        if((studentDto.getCurrentStudyLevel()!=null))
        {student.setCurrentStudyLevel(studentDto.getCurrentStudyLevel());}
        // teachingLevel
        if((studentDto.getCurrentInstitution()!=null))
        {student.setCurrentInstitution(studentDto.getCurrentInstitution());}
        // experience
        // if((studentDto.getStudyField()!=null))
       // {student.setStudyField(studentDto.getStudyField());}
        // EnrollmentYear
        if((studentDto.getEnrollmentYear()!=null))
        {student.setEnrollmentYear(studentDto.getEnrollmentYear());}
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id)
    {
        Student student = studentRepository.findStudentById(id);
        if (student == null)
        {
            throw new ApiRequestException("Student dose not exist in DB!!!");
        }
        studentRepository.delete(student);

    }
}
