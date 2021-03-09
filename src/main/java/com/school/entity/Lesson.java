package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lesson {
    @Id
    private int id;
    private Class relatedClass;
    private Teacher teacher;
    private String subject;
    private String day;

    public Lesson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class getRelatedClass() {
        return relatedClass;
    }

    public void setRelatedClass(Class relatedClass) {
        this.relatedClass = relatedClass;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
