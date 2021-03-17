package com.school.service;

import com.school.model.Student;

import java.util.List;
import java.util.Optional;

interface StudentServiceInterface {
    void saveStudent(Student student);

    boolean findIfStudentExistById(Integer id);

    List<Student> findAllStudents();

    Optional<Student> findStudentById(Integer id);

    void updateStudentWithId(Student student, int id);

    void deleteStudentWithId(int id);

    void partialUpdateStudentWithId(int id, Student student);
}
