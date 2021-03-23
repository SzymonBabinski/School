package com.school.controllers;

import com.school.dto.ClassDto;
import com.school.dto.StudentDto;
import com.school.mapper.ClassMapper;
import com.school.mapper.StudentMapper;
import com.school.service.ClassService;
import com.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ClassController {
    private final ClassService classService;
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final ClassMapper classMapper;

    ClassController(final ClassService classService, final StudentService studentService, final StudentMapper studentMapper, final ClassMapper classMapper) {
        this.classService = classService;
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.classMapper = classMapper;
    }

    @GetMapping("/classes")
    public ResponseEntity<List<ClassDto>> getClasses() {
        return ResponseEntity.ok(classService.getClasses());
    }

    @GetMapping("/classes/{className}")
    public ResponseEntity<ClassDto> getClassInfo(@PathVariable("className") String className) {
        return classService.getClassByClassName(className)
                .map(classMapper::mapToClassDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/classes/{className}/students")
    public ResponseEntity<List<StudentDto>> getClassStudentList(@PathVariable("className") String className) {
        return classService.getClassByClassName(className)
                .map(classEntity -> studentService.findAllStudentsByClassName(className))
                .map(studentMapper::mapToStudentListDto)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/classes/{className}/students/{id}")
    public ResponseEntity<StudentDto> getStudentInfo(@PathVariable("className") String className, @PathVariable("id") int id) {
        return studentService.getStudentByIdAndClassName(id, className)
                .map(studentMapper::mapToStudentDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/classes")
    public ResponseEntity<Object> createClass(@RequestBody ClassDto classDto) {
        ClassDto createdClass = classService.saveClass(classDto);
        return ResponseEntity.created(URI.create("/" + createdClass.getClassName())).body(createdClass);
    }

    @PutMapping("/classes/{className}")
    public ResponseEntity<ClassDto> updateClass(@PathVariable("className") String className, @RequestBody ClassDto classDto) {
        return classService.getClassByClassName(className)
                .map(currentClass -> classService.updateClass(currentClass, classDto))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/classes/{className}")
    public ResponseEntity<Object> deleteClass(@PathVariable("className") String className) {
        return classService.getClassByClassName(className)
                .map(currentClass -> {
                    classService.deleteClass(currentClass);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/classes/{className}")
    public ResponseEntity<ClassDto> partialUpdateClass(@PathVariable("className") String className, @RequestBody ClassDto classDto) {
        return classService.getClassByClassName(className)
                .map(currentClass -> classService.partialUpdateClass(currentClass, classDto))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
