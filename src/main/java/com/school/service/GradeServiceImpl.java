package com.school.service;

import com.school.dto.GradeDto;
import com.school.mapper.GradeMapper;
import com.school.model.Grade;
import com.school.model.Student;
import com.school.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
  private final GradeRepository gradeRepository;
  private final GradeMapper gradeMapper;

  public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper) {
    this.gradeRepository = gradeRepository;
    this.gradeMapper = gradeMapper;
  }

  public Optional<Grade> findGrade(int id) {
    return gradeRepository.findById(id);
  }

  public GradeDto showInfoAboutStudentGrade(int studentId, int gradeId) {
    return gradeMapper.mapToGradeDto(gradeRepository.getGradeByStudentIdAndId(studentId, gradeId));
  }

  public List<GradeDto> getStudentGrades(int studentId) {
    return gradeMapper.mapToGradeDto(gradeRepository.getGradesByStudentId(studentId));
  }


  public GradeDto updateStudentGrade(int studentId, int gradeId, GradeDto gradeDto) {
    Grade currentGrade = gradeRepository.getGradeByStudentIdAndId(studentId, gradeId);
    currentGrade.setValue(gradeDto.getValue());
    currentGrade.setSubject(gradeDto.getSubject());
    return gradeMapper.mapToGradeDto(gradeRepository.save(currentGrade));
  }

  public void deleteStudentGrade(int studentId, int gradeId) {
    gradeRepository.deleteGradeByIdAndStudentId(gradeId, studentId);
  }

  public GradeDto addGradeToStudent(Student student, GradeDto gradeDto) {
    return gradeMapper.mapToGradeDto(gradeRepository.save(gradeMapper.mapToGrade(gradeDto, student)));
  }
}
