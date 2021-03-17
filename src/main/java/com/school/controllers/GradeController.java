package com.school.controllers;

import com.school.dto.GradeDto;
import com.school.mapper.GradeMapper;
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

  public GradeController(
      GradeMapper gradeMapper, GradeService gradeService, StudentService studentService) {
    this.gradeMapper = gradeMapper;
    this.gradeService = gradeService;
    this.studentService = studentService;
  }

  @PostMapping("/students/{studentId}/grades")
  public ResponseEntity<GradeDto> createGradeToStudent(
      @PathVariable("studentId") int id, @RequestBody GradeDto gradeDto) {

    // Czy tutaj powinienem zwracać StudentDto czy GradeDto?
    return studentService
        .findStudentById(id)
        .map(
            student -> {
              student.getGrades().add(gradeMapper.mapToGrade(gradeDto, student));
              studentService.saveStudent(student);
              return ResponseEntity.ok().body(gradeDto);
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/students/{studentId}/grades")
  public ResponseEntity<List<GradeDto>> getGrades(@PathVariable("studentId") int id) {

    // zastanawiam sie czy zamiast student.getGrades() nie powinienem uzywać
    // gradeService.getStudentGrades() ?
    return studentService
        .findStudentById(id)
        .map(student -> ResponseEntity.ok(gradeMapper.mapToGradeDto(student.getGrades())))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/students/{studentId}/grades/{gradeId}")
  public ResponseEntity<GradeDto> gradeInfo(
      @PathVariable("studentId") int studentId, @PathVariable("gradeId") int gradeId) {

    // Nie mam pomyslu na to ale i tak jest chyba lepiej niz bylo
    if (studentService.findStudentById(studentId).isPresent()
        && gradeService.findGrade(gradeId).isPresent()) {
      return ResponseEntity.ok()
          .body(
              gradeMapper.mapToGradeDto(
                  gradeService.showInfoAboutStudentGrade(studentId, gradeId)));
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
      gradeService.updateStudentGrade(studentId, gradeId, gradeDto);
      return ResponseEntity.ok().build();
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
