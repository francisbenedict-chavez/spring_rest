package com.spring.spring_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring REST application.
 *
 * <p>
 * Starts the application and its embedded web server.
 * </p>
 */
@SpringBootApplication
public class SpringRestApplication {

  /**
   * Starts the Spring Boot application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringRestApplication.class, args);
  }

}
