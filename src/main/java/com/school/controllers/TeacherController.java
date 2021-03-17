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

        if (teacherService.findIfTeacherExistById(toCreate.getId())) {
            return ResponseEntity.notFound().build();
        }
        teacherService.saveTeacher(teacherMapper.mapToTeacher(toCreate));
        return ResponseEntity.created(URI.create("/" + toCreate.getId())).body(toCreate);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> showTeachers() {
        return ResponseEntity.ok().body(
                teacherMapper
                        .mapToTeacherListDto(teacherService.findAllTeachers())
        );
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> teacherInfo(@PathVariable int id) {
        if (!teacherService.findIfTeacherExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(teacherService.findTeacherById(id).get()));
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto toUpdate, @PathVariable int id) {
        if (!teacherService.findIfTeacherExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacherService.updateTeacherWithId(teacherMapper.mapToTeacher(toUpdate), id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/teachers/{id}")
    public ResponseEntity<?> partialUpdateTeacher(@RequestBody TeacherDto toUpdate, @PathVariable int id) {
        if (!teacherService.findIfTeacherExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacherService.partialUpdateTeachWithId(id, teacherMapper.mapToTeacher(toUpdate));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("teachers/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int id) {
        if (!teacherService.findIfTeacherExistById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacherService.deleteTeacherWithId(id);
        return ResponseEntity.ok().build();
    }


}
