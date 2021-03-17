package com.school.service;

import com.school.model.Student;
import com.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public boolean findIfStudentExistById(Integer id) {
        return studentRepository.findById(id).isPresent();
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public void updateStudentWithId(Student student, int id) {
        student.setId(id);
        studentRepository.save(student);
    }

    public void deleteStudentWithId(int id) {
        studentRepository.deleteById(id);
    }

    public void partialUpdateStudentWithId(int id, Student student) {
        Student currentStudent = findStudentById(id).get();
        if (student.getFirstName() != null) {
            currentStudent.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            currentStudent.setLastName(student.getLastName());
        }
        studentRepository.save(currentStudent);
    }

}
