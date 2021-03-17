package com.school.mapper;

import com.school.dto.GradeDto;
import com.school.model.Grade;
import com.school.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradeMapper {

  public Grade mapToGrade(final GradeDto gradeDto, Student student) {
    return new Grade(gradeDto.getValue(), gradeDto.getSubject(), student);
  }

  public GradeDto mapToGradeDto(final Grade grade) {
    return new GradeDto(
        grade.getId(), grade.getValue(), grade.getSubject(), grade.getStudent().getId());
  }

  public List<GradeDto> mapToGradeDto(final List<Grade> gradeList) {
    return gradeList.stream().map(this::mapToGradeDto).collect(Collectors.toList());
  }
}
