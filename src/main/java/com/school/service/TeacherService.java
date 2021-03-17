package com.school.service;

import com.school.model.Teacher;
import com.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements TeacherServiceInterface {
  private TeacherRepository teacherRepository;

  public TeacherService(TeacherRepository teacherRepository) {
    this.teacherRepository = teacherRepository;
  }

  public void saveTeacher(Teacher teacher) {
    teacherRepository.save(teacher);
  }

  public List<Teacher> findAllTeachers() {
    return teacherRepository.findAll();
  }

  public Optional<Teacher> findTeacherById(Integer id) {
    return teacherRepository.findById(id);
  }

  public Teacher updateTeacherWithId(Teacher teacher, int id) {
    teacher.setId(id);
    return teacherRepository.save(teacher);
  }

  public void deleteTeacherWithId(int id) {
    teacherRepository.deleteById(id);
  }

  public Teacher partialUpdateTeacherWithId(int id, Teacher teacher) {

    return findTeacherById(id)
        .map(
            teacher1 -> {
              if (teacher.getFirstName() != null) {
                teacher1.setFirstName(teacher.getFirstName());
              }
              if (teacher.getLastName() != null) {
                teacher1.setLastName(teacher.getLastName());
              }
              return teacherRepository.save(teacher1);
            })
        .orElseThrow(() -> new NullPointerException("Teacher not found"));
  }
}
