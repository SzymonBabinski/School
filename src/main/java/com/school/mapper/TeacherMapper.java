package com.school.mapper;

import com.school.dto.TeacherDto;
import com.school.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {

  public Teacher mapToTeacher(final TeacherDto teacherDto) {
    return new Teacher(teacherDto.getFirstName(), teacherDto.getLastName());
  }

  public TeacherDto mapToTeacherDto(final Teacher teacher) {
    return new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName());
  }

  public List<TeacherDto> mapToTeacherListDto(final List<Teacher> teacherList) {
    return teacherList.stream().map(this::mapToTeacherDto).collect(Collectors.toList());
  }
}
