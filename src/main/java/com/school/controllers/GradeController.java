package com.school.controllers;

import com.school.dto.GradeDto;
import com.school.mapper.GradeMapper;
import com.school.service.GradeService;
import com.school.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {
    private final StudentService studentService;
    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    GradeController(final StudentService studentService, final GradeService gradeService, final GradeMapper gradeMapper) {
        this.studentService = studentService;
        this.gradeService = gradeService;
        this.gradeMapper = gradeMapper;
    }

    @PostMapping("/students/{studentId}/grades")
    public ResponseEntity<GradeDto> createGradeToStudent(
            @PathVariable("studentId") int id, @RequestBody GradeDto gradeDto) {

        return studentService
                .findStudentById(id)
                .map(student -> gradeService.addGradeToStudent(student, gradeDto))
                .map(savedGradeDto -> ResponseEntity.ok().body(savedGradeDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/students/{studentId}/grades")
    public ResponseEntity<List<GradeDto>> getGrades(@PathVariable("studentId") int id) {

        return studentService
                .findStudentById(id)
                .map(student -> gradeService.getStudentGrades(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/students/{studentId}/grades/{gradeId}")
    public ResponseEntity<GradeDto> gradeInfo(
            @PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {

        return gradeService.getStudentGrade(studentId, gradeId)
                .map(gradeMapper::mapToGradeDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/students/{studentId}/grades/{gradeId}")
    public ResponseEntity<GradeDto> updateGrade(
            @RequestBody GradeDto gradeDto,
            @PathVariable("studentId") int studentId,
            @PathVariable("gradeId") int gradeId) {

        return gradeService.getStudentGrade(studentId, gradeId)
                .map(currentGrade -> gradeService.updateStudentGrade(currentGrade, gradeDto))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/students/{studentId}/grades/{gradeId}")
    public ResponseEntity<Object> deleteGrade(
            @PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {

        return gradeService.getStudentGrade(studentId, gradeId)
                .map(currentGrade -> {
                    gradeService.deleteStudentGrade(studentId, gradeId);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
