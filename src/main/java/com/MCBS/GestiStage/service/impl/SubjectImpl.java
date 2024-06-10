package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.SubjectDtoConverter;
import com.MCBS.GestiStage.dtos.request.SubjectDtoRequest;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.*;
import com.MCBS.GestiStage.repository.*;
import com.MCBS.GestiStage.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectImpl implements SubjectService {

    private  final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private  final SubjectDtoConverter subjectDtoConverter;
    private final TeacherRepository teacherRepository;
    private  final AppUserRepository appUserRepository;
    private  final StudyFieldRepository studyFieldRepository;

    public SubjectImpl(SubjectRepository subjectRepository, StudentRepository studentRepository, SubjectDtoConverter subjectDtoConverter, TeacherRepository teacherRepository, AppUserRepository appUserRepository, StudyFieldRepository studyFieldRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.subjectDtoConverter = subjectDtoConverter;
        this.teacherRepository = teacherRepository;
        this.appUserRepository = appUserRepository;
        this.studyFieldRepository = studyFieldRepository;
    }

    @Override
    public void createSubject(SubjectDtoRequest subject) {
        AppUser user = appUserRepository.findByEmail(subject.teacherEmail());
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        StudyField studyField = studyFieldRepository.findStudyFieldById(subject.StudyFieldId());
        if (studyField == null)
        {
            throw new ApiRequestException("This studyField dose not exist in DB!!!");
        }
        try
        {
            subjectRepository.save(Subject.builder()
                .title(subject.title())
                .description(subject.description())
                .internshipType(subject.internshipType())
                    .teacher(user)
                            .studyField(studyField)
                .build());
        }
        catch (Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Override
    public void updateSubject(SubjectDtoRequest subject, Long id) {

        Subject oldSubject = subjectRepository.findSubjectBySubjectId(id);
        if (oldSubject == null)
        {
            throw new ApiRequestException("Claim dose not exist in DB!!!");
        }
        AppUser user = appUserRepository.findByEmail(subject.teacherEmail());
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        oldSubject.setTitle(subject.title());
        oldSubject.setDescription(subject.description());
        oldSubject.setInternshipType(subject.internshipType());
        oldSubject.setTeacher(user);
        subjectRepository.save(oldSubject);
    }

    @Override
    public SubjectDtoResponse getSubjectId(Long id) {
        Subject subject = subjectRepository.findSubjectBySubjectId(id);
        if (subject == null)
        {
            throw new ApiRequestException("Claim dose not exist in DB!!!");
        }
        return subjectDtoConverter.convertToDto(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findSubjectBySubjectId(id);
        if (subject == null)
        {
            throw new ApiRequestException("Claim dose not exist in DB!!!");
        }
        subjectRepository.delete(subject);
    }

    @Override
    public List<SubjectDtoResponse> getAllSubjects(String userEmail) {
        AppUser user = appUserRepository.findByEmail(userEmail);
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        if(user instanceof Admin)
        {
            List<Subject> subjects = subjectRepository.findAll();
            List<SubjectDtoResponse> subjectDtoResponses = subjects.stream()
                    .map(subject -> subjectDtoConverter.convertToDto(subject))
                    .collect(Collectors.toList());
            return subjectDtoResponses;
        }
        else if (user instanceof Student )
        {
            StudyField studyField = null;
            Student student = studentRepository.findStudentById(user.getId());
            if(student!=null)
            {
                studyField = student.getStudyField();
            }
            List<Subject> subjects = subjectRepository.findSubjectByStudyField(studyField);
            List<SubjectDtoResponse> subjectDtoResponses = subjects.stream()
                  .map(subject -> subjectDtoConverter.convertToDto(subject))
                   .collect(Collectors.toList());
           return subjectDtoResponses;
        }
        else
        {
            Teacher teacher = teacherRepository.findTeacherById(user.getId());
            List<Subject> subjects = subjectRepository.findSubjectByTeacher(teacher);
            List<SubjectDtoResponse> subjectDtoResponses = subjects.stream()
                    .map(subject -> subjectDtoConverter.convertToDto(subject))
                    .collect(Collectors.toList());
            return subjectDtoResponses;
        }
    }
}
