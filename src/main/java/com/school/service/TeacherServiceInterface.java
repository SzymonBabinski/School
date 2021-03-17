package com.school.service;

import com.school.model.Teacher;

import java.util.List;
import java.util.Optional;

interface TeacherServiceInterface {

  void saveTeacher(Teacher teacher);

  List<Teacher> findAllTeachers();

  Optional<Teacher> findTeacherById(Integer id);

  Teacher updateTeacherWithId(Teacher teacher, int id);

  void deleteTeacherWithId(int id);

  Teacher partialUpdateTeacherWithId(int id, Teacher teacher);
}
