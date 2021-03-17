package com.school.service;

import com.school.model.Teacher;
import com.school.repository.TeacherRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public boolean findIfTeacherExistById(Integer id) {
        return teacherRepository.findById(id).isPresent();
    }

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    public void updateTeacherWithId(Teacher teacher, int id) {
        teacher.setId(id);
        teacherRepository.save(teacher);
    }

    public void deleteTeacherWithId(int id) {
        teacherRepository.deleteById(id);
    }

    public void partialUpdateTeachWithId(@Param("id") int id, Teacher teacher) {
        Teacher currentTeacher = findTeacherById(id).get();
        if (teacher.getFirstName() != null) {
            currentTeacher.setFirstName(teacher.getFirstName());
        }
        if (teacher.getLastName() != null) {
            currentTeacher.setLastName(teacher.getLastName());
        }
        teacherRepository.save(teacher);
    }

}
