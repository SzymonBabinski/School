package com.school.mapper;

import com.school.dto.StudentDto;
import com.school.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

  public Student mapToStudent(final StudentDto studentDto) {
    return new Student(studentDto.getFirstName(), studentDto.getLastName());
  }

  public StudentDto mapToStudentDto(final Student student) {
    return new StudentDto(
        student.getId(),
        student.getFirstName(),
        student.getLastName(),
        student.getGradesId(),
        student.getRelatedClass().getClassName());
  }

  public List<StudentDto> mapToStudentListDto(final List<Student> studentList) {
    return studentList.stream().map(this::mapToStudentDto).collect(Collectors.toList());
  }
}
