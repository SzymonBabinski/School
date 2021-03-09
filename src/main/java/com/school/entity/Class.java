package com.school.entity;

import javax.persistence.Entity;

@Entity
public class Class {
    private String className;
    private Teacher classTeacher;

    public Class() {
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
}
