package com.school.service;

import com.school.dto.ClassDto;
import com.school.model.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<ClassDto> getClasses();

    ClassDto saveClass(ClassDto classDto);

    Optional<Class> getClassByClassName(String className);

    ClassDto updateClass(Class currentClass, ClassDto classDto);

    void deleteClass(Class currentClass);

    ClassDto partialUpdateClass(Class currentClass, ClassDto classDto);
}
