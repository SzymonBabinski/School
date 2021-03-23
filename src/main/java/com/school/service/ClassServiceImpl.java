package com.school.service;

import com.school.dto.ClassDto;
import com.school.mapper.ClassMapper;
import com.school.model.Class;
import com.school.model.Student;
import com.school.model.Teacher;
import com.school.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final TeacherService teacherService;

    private final ClassMapper classMapper;
    private final StudentService studentService;

    ClassServiceImpl(final ClassRepository classRepository, final TeacherService teacherService, final ClassMapper classMapper, final StudentService studentService) {
        this.classRepository = classRepository;
        this.teacherService = teacherService;
        this.classMapper = classMapper;
        this.studentService = studentService;
    }

    public List<ClassDto> getClasses() {
        return classMapper.mapToClassDto(classRepository.findAll());
    }

    public ClassDto saveClass(ClassDto classDto) {
        Teacher teacher = teacherService.findTeacherById(classDto.getTeacherId()).orElseThrow(NoSuchElementException::new);
        List<Student> studentList = studentService.findAllStudentsByClassName(classDto.getClassName());
        return classMapper.mapToClassDto(classRepository.save(classMapper.mapToClass(classDto, teacher, studentList)));
    }

    public Optional<Class> getClassByClassName(String className) {
        return classRepository.getClassByClassName(className);
    }

    public ClassDto updateClass(Class currentClass, ClassDto classDto) {
        currentClass.setClassName(classDto.getClassName());
        Teacher teacher = teacherService.findTeacherById(classDto.getTeacherId()).orElseThrow(NoSuchElementException::new);
        currentClass.setClassTeacher(teacher);
        return classMapper.mapToClassDto(currentClass);
    }

    public void deleteClass(Class currentClass) {
        classRepository.delete(currentClass);
    }

    public ClassDto partialUpdateClass(Class currentClass, ClassDto classDto) {
        if (classDto.getClassName() != null) {
            currentClass.setClassName(classDto.getClassName());
        }
        if (classDto.getTeacherId() != 0) {
            Teacher teacher = teacherService.findTeacherById(classDto.getTeacherId()).orElseThrow(NoSuchElementException::new);
            currentClass.setClassTeacher(teacher);
        }
        return classMapper.mapToClassDto(currentClass);
    }
}
