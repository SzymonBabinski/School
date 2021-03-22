package com.school.repository;

import com.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> getStudentsByRelatedClassClassName(String className);

    Optional<Student> getStudentByIdAndRelatedClassClassName(int studentId, String className);
}
