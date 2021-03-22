package com.school.mapper;

import com.school.dto.ClassDto;
import com.school.model.Class;
import com.school.model.Student;
import com.school.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassMapper {

    public ClassDto mapToClassDto(final Class classGroup) {
        return new ClassDto(
                classGroup.getClassName(),
                classGroup.getClassTeacher().getId()
        );
    }

    public List<ClassDto> mapToClassDto(final List<Class> classList) {
        return classList
                .stream()
                .map(this::mapToClassDto)
                .collect(Collectors.toList());
    }

    public Class mapToClass(final ClassDto classDto, final Teacher teacher, final List<Student> studentList) {
        return new Class(
                classDto.getClassName(),
                teacher,
                studentList
        );
    }
}
