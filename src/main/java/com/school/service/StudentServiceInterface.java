package com.school.service;

import com.school.model.Student;

import java.util.List;
import java.util.Optional;

interface StudentServiceInterface {
  Student saveStudent(Student student);

  boolean findIfStudentExistById(Integer id);

  List<Student> findAllStudents();

  Optional<Student> findStudentById(Integer id);

  Student updateStudentWithId(Student student, int id);

  void deleteStudentWithId(int id);

  Student partialUpdateStudentWithId(int id, Student student);
}
