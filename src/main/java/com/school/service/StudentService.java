package com.school.service;

import com.school.dto.StudentDto;
import com.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
  StudentDto saveStudent(StudentDto studentDto);

  List<StudentDto> findAllStudents();

  Optional<Student> findStudentById(Integer id);

  StudentDto updateStudentWithId(Student currentStudent, StudentDto studentDto);

  void deleteStudentWithId(int id);

  StudentDto partialUpdateStudentWithId(Student currentStudent, StudentDto studentDto);

  List<StudentDto> findAllStudentsByClassName(String className);
}
