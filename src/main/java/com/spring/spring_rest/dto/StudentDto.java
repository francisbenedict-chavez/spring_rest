package com.spring.spring_rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * Transfers student data between the REST API and application layers.
 *
 * <p>
 * The first and last names are validated for length, and the email is
 * validated for email format.
 * </p>
 */
public class StudentDto {

  private Long id;

  @Size(min = 2, max = 50, message = "Must be 2 to 50 characters.")
  private String firstName;

  @Size(min = 2, max = 50, message = "Must be 2 to 50 characters.")
  private String lastName;

  @Email(message = "Email is required and should be valid.")
  private String email;

  /**
   * Constructs a student DTO with the supplied details.
   *
   * @param id        the student's identifier
   * @param firstName the student's first name
   * @param lastName  the student's last name
   * @param email     the student's email address
   */
  public StudentDto(Long id, String firstName, String lastName, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
