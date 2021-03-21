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
    private final StudentService studentService;
    private final StudentMapper studentMapper;

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
                        () -> {
                            StudentDto studentDto = studentService.saveStudent(toCreate);
                            return ResponseEntity.created(URI.create("/" + studentDto.getId())).body(studentDto);
                        });
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok().body(studentService.findAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> studentInfo(@PathVariable int id) {
        return studentService
                .findStudentById(id)
                .map(studentMapper::mapToStudentDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @RequestBody StudentDto toUpdate, @PathVariable int id) {
        return studentService
                .findStudentById(id)
                .map(student -> studentService.updateStudentWithId(student, toUpdate))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentDto> partialUpdateStudent(
            @RequestBody StudentDto toUpdate, @PathVariable int id) {

        return studentService
                .findStudentById(id)
                .map(student -> studentService.partialUpdateStudentWithId(student, toUpdate))
                .map(ResponseEntity::ok)
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
