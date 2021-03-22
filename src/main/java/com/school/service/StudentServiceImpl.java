package com.school.service;

import com.school.dto.StudentDto;
import com.school.mapper.StudentMapper;
import com.school.model.Student;
import com.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

  private StudentRepository studentRepository;
  private StudentMapper studentMapper;


  public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  public StudentDto saveStudent(StudentDto studentDto) {
    return studentMapper.mapToStudentDto(
            studentRepository.save(studentMapper.mapToStudent(studentDto)));
  }

  public List<Student> findAllStudentsByClassName(String className) {
    return studentRepository.getStudentsByRelatedClassClassName(className);
  }


  public List<StudentDto> findAllStudents() {
    return studentMapper.mapToStudentListDto(studentRepository.findAll());
  }

  public Optional<Student> findStudentById(Integer id) {
    return studentRepository.findById(id);
  }

  public StudentDto updateStudentWithId(Student currentStudent, StudentDto studentDto) {
    currentStudent.setFirstName(studentDto.getFirstName());
    currentStudent.setLastName(studentDto.getLastName());
    if (studentDto.getClassName() != null) {
      currentStudent.getRelatedClass().setClassName(studentDto.getClassName());
    }
    return studentMapper.mapToStudentDto(studentRepository.save(currentStudent));
  }

  public void deleteStudentWithId(int id) {
    studentRepository.deleteById(id);
  }

  public StudentDto partialUpdateStudentWithId(Student currentStudent, StudentDto studentDto) {
    if (studentDto.getFirstName() != null) {
      currentStudent.setFirstName(studentDto.getFirstName());
    }
    if (studentDto.getLastName() != null) {
      currentStudent.setLastName(studentDto.getLastName());
    }
    if (studentDto.getClassName() != null) {
      currentStudent.getRelatedClass().setClassName(studentDto.getClassName());
    }
    return studentMapper.mapToStudentDto(currentStudent);
  }

  public Optional<Student> getStudentByIdAndClassName(int studentId, String className) {
    return studentRepository.getStudentByIdAndRelatedClassClassName(studentId, className);
  }
}
