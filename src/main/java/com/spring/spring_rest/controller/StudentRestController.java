package com.spring.spring_rest.controller;

import com.spring.spring_rest.entity.Student;
import com.spring.spring_rest.exception.StudentNotFoundException;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {

  private List<Student> theStudents;

  @PostConstruct
  public void initialize() {
    theStudents = new ArrayList<>();

    theStudents.add(new Student("Light", "Yagami"));
    theStudents.add(new Student("Leslie", "Andrews"));
    theStudents.add(new Student("Emma", "Baumgarten"));
  }

  @GetMapping("/students")
  public List<Student> getStudents() {
    return theStudents;
  }

  @GetMapping("/students/{studentId}")
  public Student getStudent(@PathVariable int studentId) {

    if (studentId >= theStudents.size() || (studentId < 0)) {
      throw new StudentNotFoundException(
          "Student id " + studentId + " not found!");
    }

    return theStudents.get(studentId);
  }

}
