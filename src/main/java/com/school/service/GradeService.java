package com.school.service;

import com.school.dto.GradeDto;
import com.school.model.Grade;
import com.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface GradeService {
  Optional<Grade> findGrade(int id);

  GradeDto showInfoAboutStudentGrade(int studentId, int gradeId);

  List<GradeDto> getStudentGrades(int studentId);

  GradeDto updateStudentGrade(int studentId, int gradeId, GradeDto gradeDto);

  void deleteStudentGrade(int studentId, int gradeId);

  GradeDto addGradeToStudent(Student student, GradeDto gradeDto);
}
