package com.spring.spring_rest.controller;

import com.spring.spring_rest.entity.StudentErrorResponse;
import com.spring.spring_rest.exception.StudentNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles exceptions raised by student REST requests and converts them into
 * HTTP responses.
 *
 * <p>
 * Student-not-found errors are returned as {@code 404 Not Found};
 * validation errors and other exceptions are returned as
 * {@code 400 Bad Request}.
 * </p>
 */
@ControllerAdvice
public class StudentExceptionHandler {

  /**
   * Handles a student not found exception and returns an HTTP 404 response.
   *
   * @param exception the exception that occurred while retrieving a student
   * @return a response entity containing the error details
   */
  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(
      StudentNotFoundException exception) {

    StudentErrorResponse error = new StudentErrorResponse();

    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exception.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles an unexpected exception and returns an HTTP 400 response.
   *
   * @param exception the exception that occurred
   * @return a response entity containing the error details
   */
  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(
      Exception exception) {

    StudentErrorResponse error = new StudentErrorResponse();

    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage("Invalid data. Only integers are accepted");
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles validation errors and returns a response with field-specific
   * messages.
   *
   * @param exception the exception containing validation errors
   * @return a bad-request response containing the field error messages
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException exception) {
    Map<String, String> err = new HashMap<>();

    // extract every field error and its custom message
    exception.getBindingResult().getFieldErrors().forEach(error -> {
      err.put(error.getField(), error.getDefaultMessage());
    });

    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
  }
}
