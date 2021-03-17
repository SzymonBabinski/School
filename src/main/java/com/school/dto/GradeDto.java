package com.school.dto;

public class GradeDto {
  private final int id;
  private final int value;
  private final String subject;
  private final int studentId;

  public GradeDto(int id, int value, String subject, int studentId) {
    this.id = id;
    this.value = value;
    this.subject = subject;
    this.studentId = studentId;
  }

  public int getId() {
    return id;
  }

  public int getValue() {
    return value;
  }

  public String getSubject() {
    return subject;
  }

  public int getStudentId() {
    return studentId;
  }
}
