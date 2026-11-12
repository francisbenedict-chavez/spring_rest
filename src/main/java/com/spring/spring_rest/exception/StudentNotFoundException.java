package com.spring.spring_rest.exception;

/**
 * Indicates that a requested student could not be found.
 */
public class StudentNotFoundException extends RuntimeException {

  private static final Long serialVersionUID = -2690100046087976236L;

  public StudentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public StudentNotFoundException(String message) {
    super(message);
  }

  public StudentNotFoundException(Throwable cause) {
    super(cause);
  }

}
