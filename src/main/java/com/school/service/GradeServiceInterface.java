package com.school.service;

import com.school.dto.GradeDto;
import com.school.model.Grade;

import java.util.List;
import java.util.Optional;

interface GradeServiceInterface {
    boolean findIfGradeExistById(int id);

    Optional<Grade> findGrade(int id);

    Grade showInfoAboutStudentGrade(int studentId, int gradeId);

    List<Grade> getStudentGrades(int studentId);

    Grade updateStudentGrade(int studentId, int gradeId, GradeDto gradeDto);

    void deleteStudentGrade(int studentId, int gradeId);
}
