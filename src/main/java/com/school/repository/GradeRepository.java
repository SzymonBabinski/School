package com.school.repository;

import com.school.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
  Grade getGradeByStudentIdAndId(int studentId, int gradeId);

  List<Grade> getGradesByStudentId(int studentId);

  void deleteGradeByIdAndStudentId(int gradeId, int studentId);
}
