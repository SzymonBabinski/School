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
  private StudentService studentService;
  private StudentMapper studentMapper;

  public StudentController(StudentService studentService, StudentMapper studentMapper) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
  }

  @PostMapping("/students")
  public ResponseEntity<Object> createStudent(@RequestBody StudentDto toCreate) {
    return studentService
            .findStudentById(toCreate.getId())
            .map(student -> ResponseEntity.badRequest().build())
            .orElseGet(
                    () ->
                            ResponseEntity.created(URI.create("/" + toCreate.getId()))
                                    .body(studentService.saveStudent(toCreate)));
  }

  @GetMapping("/students")
  public ResponseEntity<List<StudentDto>> getStudents() {
    return ResponseEntity.ok().body(studentService.findAllStudents());
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<StudentDto> studentInfo(@PathVariable int id) {
    return studentService
            .findStudentById(id)
            .map(student -> ResponseEntity.ok(studentMapper.mapToStudentDto(student)))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/students/{id}")
  public ResponseEntity<StudentDto> updateStudent(
      @RequestBody StudentDto toUpdate, @PathVariable int id) {

    return studentService
            .findStudentById(id)
            .map(student -> ResponseEntity.ok(studentService.updateStudentWithId(student, toUpdate)))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/students/{id}")
  public ResponseEntity<StudentDto> partialUpdateStudent(
      @RequestBody StudentDto toUpdate, @PathVariable int id) {

    return studentService
            .findStudentById(id)
            .map(student -> ResponseEntity.ok(studentService.partialUpdateStudentWithId(student, toUpdate)))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("students/{id}")
  public ResponseEntity<Object> deleteStudent(@PathVariable int id) {

    return studentService
            .findStudentById(id)
            .map(
                    student -> {
                      studentService.deleteStudentWithId(id);
                      return ResponseEntity.noContent().build();
                    })
            .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
