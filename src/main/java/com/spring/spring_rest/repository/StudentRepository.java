package com.spring.spring_rest.repository;

import com.spring.spring_rest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides database access operations for {@link Student} entities.
 *
 * <p>
 * CRUD operations are inherited from {@link JpaRepository}.
 * </p>
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

}
