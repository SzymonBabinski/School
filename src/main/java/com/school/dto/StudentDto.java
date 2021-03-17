package com.school.dto;

import java.util.List;

public class StudentDto {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final List<Integer> gradesId;
    private final String className;


    public StudentDto(int id, String firstName, String lastName, List<Integer> gradesId, String className) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradesId = gradesId;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Integer> getGradesId() {
        return gradesId;
    }

    public String getClassName() {
        return className;
    }
}
