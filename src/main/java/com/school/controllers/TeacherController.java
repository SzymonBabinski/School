package com.school.controllers;

import com.school.dto.TeacherDto;
import com.school.mapper.TeacherMapper;
import com.school.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TeacherController {
  private TeacherService teacherService;
  private TeacherMapper teacherMapper;

  public TeacherController(TeacherService teacherService, TeacherMapper teacherMapper) {
    this.teacherService = teacherService;
    this.teacherMapper = teacherMapper;
  }

  @PostMapping("/teachers")
  public ResponseEntity<Object> createTeacher(@RequestBody TeacherDto toCreate) {
    return teacherService
            .findTeacherById(toCreate.getId())
            .map(teacher -> ResponseEntity.badRequest().build())
            .orElseGet(
                    () -> {
                      TeacherDto savedTeacher = teacherService.saveTeacher(toCreate);
                      return ResponseEntity.created(URI.create("/" + savedTeacher.getId()))
                              .body(savedTeacher);
                    });
  }

  @GetMapping("/teachers")
  public ResponseEntity<List<TeacherDto>> getTeachers() {
    return ResponseEntity.ok().body(teacherService.findAllTeachers());
  }

  @GetMapping("/teachers/{id}")
  public ResponseEntity<TeacherDto> teacherInfo(@PathVariable int id) {

    return teacherService
        .findTeacherById(id)
        .map(teacher -> ResponseEntity.ok().body(teacherMapper.mapToTeacherDto(teacher)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/teachers/{id}")
  public ResponseEntity<TeacherDto> updateTeacher(
      @RequestBody TeacherDto toUpdate, @PathVariable int id) {

    return teacherService
            .findTeacherById(id)
            .map(teacher -> ResponseEntity.ok().body(teacherService.updateTeacherWithId(teacher, toUpdate)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/teachers/{id}")
  public ResponseEntity<TeacherDto> partialUpdateTeacher(
      @RequestBody TeacherDto toUpdate, @PathVariable int id) {

    return teacherService
        .findTeacherById(id)
        .map(
                teacher ->
                        ResponseEntity.ok().body(teacherService.partialUpdateTeacherWithId(teacher, toUpdate)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("teachers/{id}")
  public ResponseEntity<Object> deleteTeacher(@PathVariable int id) {
    return teacherService
        .findTeacherById(id)
        .map(
            teacher -> {
              teacherService.deleteTeacherWithId(teacher.getId());
              return ResponseEntity.noContent().build();
            })
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
