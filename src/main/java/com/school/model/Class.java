package com.school.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "classes")
public class Class {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String className;

  @OneToOne(targetEntity = Teacher.class)
  @JoinColumn(name = "teacher_id")
  private Teacher classTeacher;

  @OneToMany(mappedBy = "relatedClass")
  private List<Student> students;

  public Class() {}

  public Class(String className, Teacher classTeacher) {
    this.className = className;
    this.classTeacher = classTeacher;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public Teacher getClassTeacher() {
    return classTeacher;
  }

  public void setClassTeacher(Teacher classTeacher) {
    this.classTeacher = classTeacher;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public List<Integer> getStudentsId() {
    return students.stream().map(student -> student.getId()).collect(Collectors.toList());
  }
}
