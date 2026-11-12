package com.spring.spring_rest.mapper;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;

/**
 * Converts student data between {@link Student} entities and
 * {@link StudentDto} objects.
 */
public class StudentMapper {

  /**
   * Converts a student entity to a data transfer object.
   *
   * @param theStudent entity to convert
   * @return a DTO containing the entity's student data
   */
  public static StudentDto mapToStudentDto(Student theStudent) {
    return new StudentDto(
        theStudent.getId(),
        theStudent.getFirstName(),
        theStudent.getLastName(),
        theStudent.getEmail());
  }

  /**
   * Converts a student data transfer object to an entity.
   *
   * @param theStudentDto DTO to convert
   * @return an entity containing the DTO's student data
   */
  public static Student mapToStudent(StudentDto theStudentDto) {
    return new Student(
        theStudentDto.getId(),
        theStudentDto.getFirstName(),
        theStudentDto.getLastName(),
        theStudentDto.getEmail());
  }

}
