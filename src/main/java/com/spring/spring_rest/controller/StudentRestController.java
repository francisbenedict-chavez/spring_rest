package com.spring.spring_rest.controller;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;
import com.spring.spring_rest.exception.StudentNotFoundException;
import com.spring.spring_rest.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides REST endpoints for creating, retrieving, updating, and deleting
 * students.
 *
 * <p>
 * The main collection endpoints delegate to {@link StudentService}.
 * This controller also contains separate in-memory demonstration endpoints.
 * </p>
 */
@RestController
@RequestMapping("/api/students")
public class StudentRestController {

  private List<Student> theStudents;

  private StudentService studentService;

  /**
   * Creates the controller with the student service used by database-backed
   * endpoints.
   *
   * @param studentService service for student operations
   */
  public StudentRestController(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * Creates a student from the validated request body.
   *
   * @param theStudentDto student data to create
   * @return the created student and an HTTP {@code 201 Created} status
   */
  @PostMapping
  public ResponseEntity<StudentDto> createStudents(
      @Valid @RequestBody StudentDto theStudentDto) {
    StudentDto saved = studentService.createStudent(theStudentDto);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  /**
   * Initializes the in-memory list used by the demonstration endpoints.
   *
   * <p>
   * This method runs after the controller has been constructed.
   * </p>
   */
  @PostConstruct
  public void initialize() {
    theStudents = new ArrayList<>();

    if (studentService.getAllStudents().isEmpty()) {
      studentService.createStudent(new StudentDto(
          null, "Light", "Yagami", "light.yagami@example.com"));
      studentService.createStudent(new StudentDto(
          null, "Leslie", "Andrews", "leslie.andrews@example.com"));
      studentService.createStudent(new StudentDto(
          null, "Emma", "Baumgarten", "emma.baumgarten@example.com"));
    }
  }

  /**
   * Returns all students in the in-memory demonstration list.
   *
   * @return the demonstration student list
   */
  @GetMapping("/students")
  public List<Student> getStudents() {
    return theStudents;
  }

  /**
   * Returns a student from the in-memory demonstration list by zero-based
   * list index.
   *
   * @param studentId zero-based index of the student in the list
   * @return the student at that index
   * @throws StudentNotFoundException if the index is negative or outside the
   *                                  list
   */
  @GetMapping("/students/{studentId}")
  public Student getStudent(@PathVariable int studentId) {

    if (studentId >= theStudents.size() || (studentId < 0)) {
      throw new StudentNotFoundException(
          "Student id " + studentId + " not found!");
    }

    return theStudents.get(studentId);
  }

  /**
   * Retrieves a student from the database by ID.
   *
   * @param id database ID of the student to retrieve
   * @return the student and an HTTP {@code 200 OK} status
   * @throws StudentNotFoundException if no student has the specified ID
   */
  @GetMapping("{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    StudentDto studentDto = studentService.getStudentById(id);

    return ResponseEntity.ok(studentDto);
  }

  /**
   * Retrieves all students from the database.
   *
   * @return the students and an HTTP {@code 200 OK} status
   */
  @GetMapping
  public ResponseEntity<List<StudentDto>> getAllStudents() {
    List<StudentDto> students = studentService.getAllStudents();

    return ResponseEntity.ok(students);
  }

  /**
   * Replaces the data of a student in the database.
   *
   * @param id            database ID of the student to update
   * @param theStudentDto replacement student data
   * @return the updated student and an HTTP {@code 200 OK} status
   * @throws StudentNotFoundException if no student has the specified ID
   */
  @PutMapping("{id}")
  public ResponseEntity<StudentDto> updateStudent(
      @PathVariable Long id, @Valid @RequestBody StudentDto theStudentDto) {
    StudentDto studentDto = studentService.updateStudent(id, theStudentDto);

    return ResponseEntity.ok(studentDto);
  }

  /**
   * Updates the non-null fields of a student in the database.
   *
   * @param id            database ID of the student to update
   * @param theStudentDto student fields to apply
   * @return the updated student and an HTTP {@code 200 OK} status
   * @throws StudentNotFoundException if no student has the specified ID
   */
  @PatchMapping("{id}")
  public ResponseEntity<StudentDto> patchStudent(
      @PathVariable Long id, @Valid @RequestBody StudentDto theStudentDto) {
    StudentDto studentDto = studentService.patchStudent(id, theStudentDto);

    return ResponseEntity.ok(studentDto);
  }

  /**
   * Deletes the student with the specified ID.
   *
   * @param id ID of the student to delete
   * @return a success message and an HTTP {@code 200 OK} status
   * @throws StudentNotFoundException if no student has the specified ID
   */
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteStudentById(
      @PathVariable Long id) {
    studentService.deleteStudentById(id);
    return ResponseEntity.ok("Student deleted successfully!");
  }
}
