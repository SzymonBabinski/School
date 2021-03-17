package com.school.mapper;

import com.school.dto.GradeDto;
import com.school.model.Grade;
import com.school.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradeMapper {

    private StudentService studentService;

    public GradeMapper(StudentService studentService) {
        this.studentService = studentService;
    }

    public Grade mapToGrade(final GradeDto gradeDto, int studentId) {
        return new Grade(
                gradeDto.getValue(),
                gradeDto.getSubject(),
                studentService.findStudentById(studentId).get()
        );
    }

    public GradeDto mapToGradeDto(final Grade grade) {
        return new GradeDto(
                grade.getId(),
                grade.getValue(),
                grade.getSubject(),
                grade.getStudent().getId()
        );
    }

    public List<GradeDto> mapToGradeDto(final List<Grade> gradeList) {
        return gradeList
                .stream()
                .map(this::mapToGradeDto)
                .collect(Collectors.toList());
    }
}

