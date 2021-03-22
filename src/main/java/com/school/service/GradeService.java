package com.school.service;

import com.school.dto.GradeDto;
import com.school.model.Grade;
import com.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface GradeService {
  Optional<Grade> findGrade(int id);

  Optional<Grade> getStudentGrade(int studentId, int gradeId);

  List<GradeDto> getStudentGrades(int studentId);

  GradeDto updateStudentGrade(Grade currentGrade, GradeDto gradeDto);

  void deleteStudentGrade(int gradeId, int studentId);

  GradeDto addGradeToStudent(Student student, GradeDto gradeDto);
}
