package com.school.service;

import com.school.dto.TeacherDto;
import com.school.mapper.TeacherMapper;
import com.school.model.Teacher;
import com.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
  private TeacherRepository teacherRepository;
  private TeacherMapper teacherMapper;

  public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
    this.teacherRepository = teacherRepository;
    this.teacherMapper = teacherMapper;
  }

  public TeacherDto saveTeacher(TeacherDto teacherDto) {
    return teacherMapper.mapToTeacherDto(
            teacherRepository.save(teacherMapper.mapToTeacher(teacherDto)));
  }

  public List<TeacherDto> findAllTeachers() {
    return teacherMapper.mapToTeacherListDto(teacherRepository.findAll());
  }

  public Optional<Teacher> findTeacherById(Integer id) {
    return teacherRepository.findById(id);
  }

  public TeacherDto updateTeacherWithId(Teacher currentTeacher, TeacherDto teacherDto) {
    currentTeacher.setFirstName(teacherDto.getFirstName());
    currentTeacher.setLastName(teacherDto.getLastName());
    return teacherMapper.mapToTeacherDto(
            teacherRepository.save(teacherMapper.mapToTeacher(teacherDto)));
  }

  public void deleteTeacherWithId(int id) {
    teacherRepository.deleteById(id);
  }

  public TeacherDto partialUpdateTeacherWithId(Teacher currentTeacher, TeacherDto teacherDto) {

    if (teacherDto.getFirstName() != null) {
      currentTeacher.setFirstName(teacherDto.getFirstName());
    }
    if (teacherDto.getLastName() != null) {
      currentTeacher.setLastName(teacherDto.getLastName());
    }
    return teacherMapper.mapToTeacherDto(teacherRepository.save(currentTeacher));
  }
}
