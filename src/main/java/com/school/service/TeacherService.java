package com.school.service;

import com.school.dto.TeacherDto;
import com.school.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

  TeacherDto saveTeacher(TeacherDto teacherDto);

  List<TeacherDto> findAllTeachers();

  Optional<Teacher> findTeacherById(Integer id);

  TeacherDto updateTeacherWithId(Teacher currentTeacher, TeacherDto teacherDto);

  void deleteTeacherWithId(int id);

  TeacherDto partialUpdateTeacherWithId(Teacher currentTeacher, TeacherDto teacherDto);
}
