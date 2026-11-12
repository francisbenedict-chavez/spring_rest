package com.spring.spring_rest.service;

import com.spring.spring_rest.dto.StudentDto;
import java.util.List;

public interface StudentService {

  StudentDto createStudent(StudentDto theStudentDto);

  StudentDto getStudentById(Long theId);

  List<StudentDto> getAllStudents();

  StudentDto updateStudent(Long theId, StudentDto theStudentDto);

  StudentDto patchStudent(Long theId, StudentDto thStudentDto);

  void deleteStudentById(Long theId);
}
