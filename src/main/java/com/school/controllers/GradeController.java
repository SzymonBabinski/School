package com.school.controllers;

import com.school.dto.GradeDto;
import com.school.service.GradeService;
import com.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {
  private StudentService studentService;
  private GradeService gradeService;

  public GradeController(StudentService studentService, GradeService gradeService) {
    this.studentService = studentService;
    this.gradeService = gradeService;
  }

  @PostMapping("/students/{studentId}/grades")
  public ResponseEntity<GradeDto> createGradeToStudent(
      @PathVariable("studentId") int id, @RequestBody GradeDto gradeDto) {

    return studentService
            .findStudentById(id)
            .map(student -> gradeService.addGradeToStudent(student, gradeDto))
            .map(savedGradeDto -> ResponseEntity.ok().body(savedGradeDto))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/students/{studentId}/grades")
  public ResponseEntity<List<GradeDto>> getGrades(@PathVariable("studentId") int id) {

    return studentService
            .findStudentById(id)
            .map(student -> ResponseEntity.ok(gradeService.getStudentGrades(id)))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/students/{studentId}/grades/{gradeId}")
  public ResponseEntity<GradeDto> gradeInfo(
      @PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {

    if (studentService.findStudentById(studentId).isPresent()
        && gradeService.findGrade(gradeId).isPresent()) {
      return ResponseEntity.ok().body(gradeService.showInfoAboutStudentGrade(studentId, gradeId));

    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/students/{studentId}/grades/{gradeId}")
  public ResponseEntity<GradeDto> updateGrade(
      @RequestBody GradeDto gradeDto,
      @PathVariable("studentId") int studentId,
      @PathVariable("gradeId") int gradeId) {

    if (studentService.findStudentById(studentId).isPresent()
        && gradeService.findGrade(gradeId).isPresent()) {
      return ResponseEntity.ok()
              .body(gradeService.updateStudentGrade(studentId, gradeId, gradeDto));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/students/{studentId}/grades/{gradeId}")
  public ResponseEntity<GradeDto> deleteGrade(
      @PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {

    if (studentService.findStudentById(studentId).isPresent()
        && gradeService.findGrade(gradeId).isPresent()) {
      gradeService.deleteStudentGrade(studentId, gradeId);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
