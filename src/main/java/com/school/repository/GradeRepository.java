package com.school.repository;

import com.school.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

  @Query(
      value = "SELECT id,value,subject FROM grades WHERE id =:gradeId AND student_id =:studentId",
      nativeQuery = true)
  Grade showInfoAboutStudentGrade(@Param("studentId") int studentId, @Param("gradeId") int gradeId);

  @Query(value = "SELECT * FROM grades WHERE student_id =:studentId", nativeQuery = true)
  List<Grade> getStudentGradesList(@Param("studentId") int studentId);

  @Modifying
  @Transactional
  @Query(
      value =
          "update GRADES set VALUE =:gradeValue, SUBJECT =:subject, STUDENT_ID=:studentId WHERE ID =:gradeId",
      nativeQuery = true)
  void updateGrade(
      @Param("gradeId") int id,
      @Param("gradeValue") int value,
      @Param("subject") String subject,
      @Param("studentId") int studentId);

  @Modifying
  @Transactional
  @Query(
      value = "delete from GRADES where ID =:gradeId AND STUDENT_ID =:studentId",
      nativeQuery = true)
  void deleteGrade(@Param("studentId") int studentId, @Param("gradeId") int id);
}
