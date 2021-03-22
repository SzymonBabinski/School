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
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    public TeacherController(TeacherService teacherService, TeacherMapper teacherMapper) {
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
    }

    @PostMapping("/teachers")
    public ResponseEntity<Object> createTeacher(@RequestBody TeacherDto toCreate) {
        TeacherDto savedTeacher = teacherService.saveTeacher(toCreate);
        return ResponseEntity.created(URI.create("/" + savedTeacher.getId()))
                .body(savedTeacher);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        return ResponseEntity.ok().body(teacherService.findAllTeachers());
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> teacherInfo(@PathVariable int id) {

        return teacherService
                .findTeacherById(id)
                .map(teacherMapper::mapToTeacherDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(
            @RequestBody TeacherDto toUpdate, @PathVariable int id) {

        return teacherService
                .findTeacherById(id)
                .map(teacher -> teacherService.updateTeacherWithId(teacher, toUpdate))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> partialUpdateTeacher(
            @RequestBody TeacherDto toUpdate, @PathVariable int id) {

        return teacherService
                .findTeacherById(id)
                .map(teacher -> teacherService.partialUpdateTeacherWithId(teacher, toUpdate))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("teachers/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable int id) {
        return teacherService
                .findTeacherById(id)
                .map(
                        teacher -> {
                            teacherService.deleteTeacherWithId(teacher.getId());
                            return ResponseEntity.ok().build();
                        })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
