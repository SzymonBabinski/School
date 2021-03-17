package com.school.service;

import com.school.model.Student;
import com.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  private StudentRepository studentRepository;

  public Student saveStudent(Student student) {
    return studentRepository.save(student);
  }

  public List<Student> findAllStudents() {
    return studentRepository.findAll();
  }

  public Optional<Student> findStudentById(Integer id) {
    return studentRepository.findById(id);
  }

  public Student updateStudentWithId(Student student, int id) {
    student.setId(id);
    return studentRepository.save(student);
  }

  public void deleteStudentWithId(int id) {
    studentRepository.deleteById(id);
  }

  public Student partialUpdateStudentWithId(int id, Student student) {

    return findStudentById(id)
        .map(
            currentStudent -> {
              if (student.getFirstName() != null) {
                currentStudent.setFirstName(student.getFirstName());
              }
              if (student.getLastName() != null) {
                currentStudent.setLastName(student.getLastName());
              }
              studentRepository.save(currentStudent);
              return currentStudent;
            })
        .orElseThrow(() -> new NullPointerException("Student not found"));
  }
}
