package com.school.repository;

import com.school.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, String> {
    Optional<Class> getClassByClassName(String className);
}
