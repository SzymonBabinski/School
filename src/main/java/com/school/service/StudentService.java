package com.school.service;

import com.school.dto.StudentDto;
import com.school.model.Student;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface StudentService {
  StudentDto saveStudent(StudentDto studentDto) throws NotFoundException;

  List<StudentDto> findAllStudents();

  Optional<Student> findStudentById(Integer id);

  StudentDto updateStudentWithId(Student currentStudent, StudentDto studentDto) throws NotFoundException;

  void deleteStudentWithId(int id);

  StudentDto partialUpdateStudentWithId(Student currentStudent, StudentDto studentDto) throws NotFoundException;

  List<Student> findAllStudentsByClassName(String className);

  Optional<Student> getStudentByIdAndClassName(int studentId, String className);
}
