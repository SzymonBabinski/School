package com.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstName;
  private String lastName;

  @OneToMany(targetEntity = Grade.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "student_id")
  private List<Grade> grades = new ArrayList<>();

  @ManyToOne(targetEntity = Class.class)
  @JoinColumn(name = "student_class_name")
  private Class relatedClass;

  public Student() {}

  public Student(String firstName, String lastName, List<Grade> grades, Class relatedClass) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.grades = grades;
    this.relatedClass = relatedClass;
  }

  public Student(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firsName) {
    this.firstName = firsName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Grade> getGrades() {
    return grades;
  }

  public void setGrades(List<Grade> grades) {
    this.grades = grades;
  }

  public Class getRelatedClass() {
    if (relatedClass == null) {
      return new Class();
    }
    return relatedClass;
  }

  public void setRelatedClass(Class relatedClass) {
    this.relatedClass = relatedClass;
  }

  public List<Integer> getGradesId() {
    return grades.stream().map(Grade::getId).collect(Collectors.toList());
  }

  public List<Integer> getGradesValue() {
    return grades.stream().map(Grade::getId).collect(Collectors.toList());
  }

  public Grade getGradeById(int id) {
    return grades.stream().filter(grade -> id == grade.getId()).findAny().orElse(null);
  }
}
