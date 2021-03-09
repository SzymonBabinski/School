package com.school.entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firsName;
    private String lastName;
    @ManyToOne
    private Class relatedClass;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Class getRelatedClass() {
        return relatedClass;
    }

    public void setRelatedClass(Class relatedClass) {
        this.relatedClass = relatedClass;
    }

}
