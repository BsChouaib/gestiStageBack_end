package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.StudyField;
import com.MCBS.GestiStage.models.Subject;
import com.MCBS.GestiStage.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Subject findSubjectBySubjectId(Long id);
    List<Subject> findSubjectByStudyField(StudyField StudyField);
    List<Subject> findSubjectByTeacher(Teacher teacher);

}
