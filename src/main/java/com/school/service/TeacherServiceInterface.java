package com.school.service;

import com.school.model.Teacher;

import java.util.List;
import java.util.Optional;

interface TeacherServiceInterface {

    void saveTeacher(Teacher teacher);

    boolean findIfTeacherExistById(Integer id);

    List<Teacher> findAllTeachers();

    Optional<Teacher> findTeacherById(Integer id);

    void updateTeacherWithId(Teacher teacher, int id);

    void deleteTeacherWithId(int id);

    void partialUpdateTeachWithId(int id, Teacher teacher);
}
