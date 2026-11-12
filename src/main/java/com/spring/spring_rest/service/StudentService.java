package com.spring.spring_rest.service;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.exception.StudentNotFoundException;

import java.util.List;

/**
 * Defines operations for managing students.
 */
public interface StudentService {

  /**
   * Creates a student.
   *
   * @param theStudentDto student data to create
   * @return the created student
   */
  StudentDto createStudent(StudentDto theStudentDto);

  /**
   * Retrieves a student by ID.
   *
   * @param theId ID of the student to retrieve
   * @return the requested student
   * @throws StudentNotFoundException if no student has the specified ID
   */
  StudentDto getStudentById(Long theId);

  /**
   * Retrieves all students.
   *
   * @return all students
   */
  List<StudentDto> getAllStudents();

  /**
   * Replaces the data of an existing student.
   *
   * @param theId         ID of the student to update
   * @param theStudentDto replacement student data
   * @return the updated student
   * @throws StudentNotFoundException if no student has the specified ID
   */
  StudentDto updateStudent(Long theId, StudentDto theStudentDto);

  /**
   * Updates the non-null fields of an existing student.
   *
   * @param theId        ID of the student to update
   * @param thStudentDto student fields to apply
   * @return the updated student
   * @throws StudentNotFoundException if no student has the specified ID
   */
  StudentDto patchStudent(Long theId, StudentDto thStudentDto);

  /**
   * Deletes a student by ID.
   *
   * @param theId ID of the student to delete
   * @throws StudentNotFoundException if no student has the specified ID
   */
  void deleteStudentById(Long theId);
}
