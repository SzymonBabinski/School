package com.school.service;

import com.school.dto.GradeDto;
import com.school.model.Grade;
import com.school.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService implements GradeServiceInterface {
  private GradeRepository gradeRepository;

  public GradeService(GradeRepository gradeRepository) {
    this.gradeRepository = gradeRepository;
  }

  public Optional<Grade> findGrade(int id) {
    return gradeRepository.findById(id);
  }

  public Grade showInfoAboutStudentGrade(int studentId, int gradeId) {
    return gradeRepository.showInfoAboutStudentGrade(studentId, gradeId);
  }

  public List<Grade> getStudentGrades(int studentId) {
    return gradeRepository.getStudentGradesList(studentId);
  }

  // Nie wiem czy wgl mozna z native query zwracac cos innego niz void
  public void updateStudentGrade(int studentId, int gradeId, GradeDto gradeDto) {
    gradeRepository.updateGrade(gradeId, gradeDto.getValue(), gradeDto.getSubject(), studentId);
  }

  public void deleteStudentGrade(int studentId, int gradeId) {
    gradeRepository.deleteGrade(studentId, gradeId);
  }
}
