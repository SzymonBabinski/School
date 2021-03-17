package com.school.controllers;

import com.school.dto.StudentDto;
import com.school.mapper.StudentMapper;
import com.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {

  public StudentController(StudentService studentService, StudentMapper studentMapper) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
  }

  private StudentService studentService;

  private StudentMapper studentMapper;

  @PostMapping("/students")
  public ResponseEntity<Object> createStudent(@RequestBody StudentDto toCreate) {
    return studentService.findStudentById(toCreate.getId()).map(student -> ResponseEntity.notFound().build()).orElse(ResponseEntity.created(URI.create("/" + toCreate.getId())).body(studentMapper.mapToStudentDto(studentService.saveStudent(studentMapper.mapToStudent(toCreate)))));
  }

  @GetMapping("/students")
  public ResponseEntity<List<StudentDto>> getStudents() {
    return ResponseEntity.ok().body(studentMapper.mapToStudentListDto(studentService.findAllStudents()));
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<StudentDto> studentInfo(@PathVariable int id) {
    return studentService.findStudentById(id).map(student -> ResponseEntity.ok(studentMapper.mapToStudentDto(student))).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/students/{id}")
  public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto toUpdate, @PathVariable int id) {

    return studentService.findStudentById(id).map(student -> ResponseEntity.ok(studentMapper.mapToStudentDto(studentService.updateStudentWithId(studentMapper.mapToStudent(toUpdate), id)))).orElse(ResponseEntity.notFound().build());
  }

  @PatchMapping("/students/{id}")
  public ResponseEntity<StudentDto> partialUpdateStudent(@RequestBody StudentDto toUpdate, @PathVariable int id) {

    return studentService.findStudentById(id).map(student -> ResponseEntity.ok(studentMapper.mapToStudentDto(studentService.partialUpdateStudentWithId(id, studentMapper.mapToStudent(toUpdate))))).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("students/{id}")
  public ResponseEntity<Object> deleteStudent(@PathVariable int id) {

    return studentService.findStudentById(id).map(student -> {
      studentService.deleteStudentWithId(id);
      return ResponseEntity.noContent().build();
    }).orElse(ResponseEntity.notFound().build());
  }
}
