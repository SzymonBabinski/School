package com.school.dto;

public class ClassDto {
    private final String className;
    private final int teacherId;

    public ClassDto(String className, int teacherId) {
        this.className = className;
        this.teacherId = teacherId;
    }

    public String getClassName() {
        return className;
    }

    public int getTeacherId() {
        return teacherId;
    }

}
