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
  private TeacherMapper teacherMapper;

  private TeacherService teacherService;

  public TeacherController(TeacherMapper teacherMapper, TeacherService teacherService) {
    this.teacherMapper = teacherMapper;
    this.teacherService = teacherService;
  }

  @PostMapping("/teachers")
  public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto toCreate) {
    return teacherService.findTeacherById(toCreate.getId()).map(teacher -> ResponseEntity.badRequest().body(teacherMapper.mapToTeacherDto(teacher))).orElseGet(() -> {
      teacherService.saveTeacher(teacherMapper.mapToTeacher(toCreate));
      return ResponseEntity.created(URI.create("/" + toCreate.getId())).body(toCreate);
    });
  }

  @GetMapping("/teachers")
  public ResponseEntity<List<TeacherDto>> getTeachers() {
    return ResponseEntity.ok().body(teacherMapper.mapToTeacherListDto(teacherService.findAllTeachers()));
  }

  @GetMapping("/teachers/{id}")
  public ResponseEntity<TeacherDto> teacherInfo(@PathVariable int id) {

    return teacherService.findTeacherById(id).map(teacher -> ResponseEntity.ok().body(teacherMapper.mapToTeacherDto(teacher))).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/teachers/{id}")
  public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto toUpdate, @PathVariable int id) {

    return teacherService.findTeacherById(id).map(teacher -> ResponseEntity.ok().body(teacherMapper.mapToTeacherDto(teacherService.updateTeacherWithId(teacherMapper.mapToTeacher(toUpdate), id)))).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/teachers/{id}")
  public ResponseEntity<TeacherDto> partialUpdateTeacher(@RequestBody TeacherDto toUpdate, @PathVariable int id) {

    return teacherService.findTeacherById(id).map(teacher -> ResponseEntity.ok().body(teacherMapper.mapToTeacherDto(teacherService.partialUpdateTeacherWithId(id, teacherMapper.mapToTeacher(toUpdate))))).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("teachers/{id}")
  public ResponseEntity<Object> deleteTeacher(@PathVariable int id) {
    return teacherService.findTeacherById(id).map(teacher -> {
      teacherService.deleteTeacherWithId(teacher.getId());
      return ResponseEntity.noContent().build();
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
