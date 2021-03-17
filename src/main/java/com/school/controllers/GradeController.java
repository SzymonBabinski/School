package com.school.controllers;

import com.school.dto.GradeDto;
import com.school.mapper.GradeMapper;
import com.school.model.Grade;
import com.school.model.Student;
import com.school.service.GradeService;
import com.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GradeController {
    private GradeMapper gradeMapper;
    private GradeService gradeService;
    private StudentService studentService;

    public GradeController(GradeMapper gradeMapper, GradeService gradeService, StudentService studentService) {
        this.gradeMapper = gradeMapper;
        this.gradeService = gradeService;
        this.studentService = studentService;
    }

    @PostMapping("/students/{studentId}/grades")
    public ResponseEntity<GradeDto> createGradeToStudent(@PathVariable("studentId") int id, @RequestBody GradeDto gradeDto) {
        if (!studentService.findIfStudentExistById(id)) {
            return ResponseEntity.notFound().build();
        }

        Student student = studentService.findStudentById(id).get();
        student.getGrades().add(gradeMapper.mapToGrade(gradeDto, id));
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/students/{studentId}/grades")
    public ResponseEntity<List<GradeDto>> showGrades(@PathVariable("studentId") int id) {
        if (!studentService.findIfStudentExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<Grade> grade = gradeService.getStudentGrades(id);
        return ResponseEntity.ok(gradeMapper.mapToGradeDto(grade));
    }

    @GetMapping("/students/{studentId}/grades/{gradeId}")
    public ResponseEntity<GradeDto> gradeInfo(@PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {
        if (!studentService.findIfStudentExistById(studentId) || !gradeService.findIfGradeExistById(gradeId)) {
            return ResponseEntity.notFound().build();
        }
        GradeDto gradeDto = gradeMapper.mapToGradeDto(gradeService.showInfoAboutStudentGrade(studentId, gradeId));
        return ResponseEntity.ok().body(gradeDto);
    }

    @PutMapping("/students/{studentId}/grades/{gradeId}")
    public ResponseEntity<GradeDto> updateGrade(@RequestBody GradeDto gradeDto, @PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {
        if (!studentService.findIfStudentExistById(studentId) || !gradeService.findIfGradeExistById(gradeId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(gradeMapper.mapToGradeDto(gradeService.updateStudentGrade(studentId, gradeId, gradeDto)));
    }

    @DeleteMapping("/students/{studentId}/grades/{gradeId}")
    public ResponseEntity<GradeDto> deleteGrade(@PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {
        if (!studentService.findIfStudentExistById(studentId) || !gradeService.findIfGradeExistById(gradeId)) {
            return ResponseEntity.notFound().build();
        }
        gradeService.deleteStudentGrade(studentId, gradeId);
        return ResponseEntity.ok().build();
    }


}
