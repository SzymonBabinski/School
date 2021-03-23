package com.school.service;

import com.school.dto.StudentDto;
import com.school.mapper.StudentMapper;
import com.school.model.Class;
import com.school.model.Student;
import com.school.repository.StudentRepository;
import javassist.NotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClassService classService;

    @Lazy
    StudentServiceImpl(final StudentRepository studentRepository, final StudentMapper studentMapper, final ClassService classService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.classService = classService;
    }

    public StudentDto saveStudent(StudentDto studentDto) throws NotFoundException {
        Class studentClass = classService.getClassByClassName(studentDto.getClassName()).orElseThrow(() -> new NotFoundException("Class " + studentDto.getClassName() + " not found"));
        return studentMapper.mapToStudentDto(
                studentRepository.save(studentMapper.mapToStudent(studentDto, studentClass)));
    }

    public List<Student> findAllStudentsByClassName(String className) {
        return studentRepository.getStudentsByRelatedClassClassName(className);
    }


    public List<StudentDto> findAllStudents() {
        return studentMapper.mapToStudentListDto(studentRepository.findAll());
    }

    public Optional<Student> findStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public StudentDto updateStudentWithId(Student currentStudent, StudentDto studentDto) throws NotFoundException {
        currentStudent.setFirstName(studentDto.getFirstName());
        currentStudent.setLastName(studentDto.getLastName());
        if (studentDto.getClassName() != null) {
            Class studentClass = classService.getClassByClassName(studentDto.getClassName()).orElseThrow(() -> new NotFoundException("Class " + studentDto.getClassName() + " not found"));
            currentStudent.setRelatedClass(studentClass);
        }
        studentRepository.save(currentStudent);
        return studentMapper.mapToStudentDto(studentRepository.save(currentStudent));
    }

    public void deleteStudentWithId(int id) {
        studentRepository.deleteById(id);
    }

    public StudentDto partialUpdateStudentWithId(Student currentStudent, StudentDto studentDto) throws NotFoundException {
        if (studentDto.getFirstName() != null) {
            currentStudent.setFirstName(studentDto.getFirstName());
        }
        if (studentDto.getLastName() != null) {
            currentStudent.setLastName(studentDto.getLastName());
        }
        if (studentDto.getClassName() != null) {
            Class studentClass = classService.getClassByClassName(studentDto.getClassName()).orElseThrow(() -> new NotFoundException("Class " + studentDto.getClassName() + " not found"));
            currentStudent.setRelatedClass(studentClass);
        }
        studentRepository.save(currentStudent);
        return studentMapper.mapToStudentDto(currentStudent);
    }

    public Optional<Student> getStudentByIdAndClassName(int studentId, String className) {
        return studentRepository.getStudentByIdAndRelatedClassClassName(studentId, className);
    }
}
