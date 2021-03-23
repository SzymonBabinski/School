package com.school.controllers;

import com.school.dto.StudentDto;
import com.school.mapper.StudentMapper;
import com.school.service.StudentService;
import javassist.NotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {
    @Lazy
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody StudentDto toCreate) throws NotFoundException {
        StudentDto studentDto = studentService.saveStudent(toCreate);
        return ResponseEntity.created(URI.create("/" + studentDto.getId())).body(studentDto);
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

    // TODO: Handle exception in different way and refactor
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(
            @RequestBody StudentDto toUpdate, @PathVariable int id) throws NotFoundException {
        return studentService
                .findStudentById(id)
                .map(student -> {
                    try {
                        return ResponseEntity.ok(studentService.updateStudentWithId(student, toUpdate));
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                        return ResponseEntity.notFound().build();
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO: Handle exception in different way and refactor
    @PatchMapping("/students/{id}")
    public ResponseEntity<?> partialUpdateStudent(
            @RequestBody StudentDto toUpdate, @PathVariable int id) {

        return studentService
                .findStudentById(id)
                .map(student -> {
                    try {
                        return ResponseEntity.ok(studentService.partialUpdateStudentWithId(student, toUpdate));
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                        return ResponseEntity.notFound().build();
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) {

        return studentService
                .findStudentById(id)
                .map(
                        student -> {
                            studentService.deleteStudentWithId(id);
                            return ResponseEntity.ok().build();
                        })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
