package com.school.service;

import com.school.dto.GradeDto;
import com.school.mapper.GradeMapper;
import com.school.model.Grade;
import com.school.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService implements GradeServiceInterface {
    private GradeRepository gradeRepository;
    private GradeMapper gradeMapper;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }


    public boolean findIfGradeExistById(int id) {
        return gradeRepository.findById(id).isPresent();
    }

    public Optional<Grade> findGrade(int id) {
        return gradeRepository.findById(id);
    }

    public Grade showInfoAboutStudentGrade(int studentId, int gradeId) {
        return gradeRepository.showInfoAboutStudentGrade(studentId, gradeId);
    }

    public List<Grade> getStudentGrades(int studentId) {
        return gradeRepository.getStudentGradesList(studentId);
    }

    public Grade updateStudentGrade(int studentId, int gradeId, GradeDto gradeDto) {
        gradeRepository.updateGrade(gradeId, gradeDto.getValue(), gradeDto.getSubject(), studentId);
        return gradeMapper.mapToGrade(gradeDto, studentId);
    }

    public void deleteStudentGrade(int studentId, int gradeId) {
        gradeRepository.deleteGrade(studentId, gradeId);
    }


}
