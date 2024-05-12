package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.SubjectDtoConverter;
import com.MCBS.GestiStage.dtos.request.SubjectDtoRequest;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Subject;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.SubjectRepository;
import com.MCBS.GestiStage.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectImpl implements SubjectService {

    private  final SubjectRepository subjectRepository;

    private  final SubjectDtoConverter subjectDtoConverter;
    private  final AppUserRepository appUserRepository;

    public SubjectImpl(SubjectRepository subjectRepository, SubjectDtoConverter subjectDtoConverter, AppUserRepository appUserRepository) {
        this.subjectRepository = subjectRepository;
        this.subjectDtoConverter = subjectDtoConverter;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void createSubject(SubjectDtoRequest subject) {
        AppUser user = appUserRepository.findByEmail(subject.teacherEmail());
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        try
        {
            subjectRepository.save(Subject.builder()
                .title(subject.title())
                .description(subject.description())
                .internshipType(subject.internshipType())
                            .tEmail(subject.teacherEmail())
                    .teacher(user)
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
    public List<SubjectDtoResponse> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDtoResponse> subjectDtoResponses = subjects.stream()
                .map(subject -> subjectDtoConverter.convertToDto(subject))
                .collect(Collectors.toList());
        return subjectDtoResponses;
    }
}
