package com.students.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.students.entity.Student;
/**
 * @author Shakti Shekhawat
 * @version 1.0 
 * StudentRepository extends JpaRepository to perform all the task on database like CRUD operations.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
