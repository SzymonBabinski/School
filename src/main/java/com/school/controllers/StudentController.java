package com.school.controllers;

import com.school.dto.StudentDto;
import com.school.mapper.StudentMapper;
import com.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createStudent(@RequestBody StudentDto toCreate) {
        // TODO: Jak zrobic mapowanie na ResponseEntity.created() zamiast ok?
        return studentService.findStudentById(toCreate.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> showStudents() {
        return ResponseEntity.ok().body(
                studentMapper
                        .mapToStudentListDto(studentService.findAllStudents())
        );
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> studentInfo(@PathVariable int id) {
        return studentService.findStudentById(id).map(student -> ResponseEntity.ok(studentMapper.mapToStudentDto(student))).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto toUpdate, @PathVariable int id) {
        if (!studentService.findIfStudentExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentService.updateStudentWithId(studentMapper.mapToStudent(toUpdate), id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<?> partialUpdateStudent(@RequestBody StudentDto toUpdate, @PathVariable int id) {
        if (!studentService.findIfStudentExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentService.partialUpdateStudentWithId(id, studentMapper.mapToStudent(toUpdate));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        if (!studentService.findIfStudentExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteStudentWithId(id);
        return ResponseEntity.ok().build();
    }

}
