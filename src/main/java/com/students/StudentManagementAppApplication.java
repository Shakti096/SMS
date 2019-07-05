package com.students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Shakti Shekhawat
 * @version 1.0
 * 
 **********************************************
 *          Student Management application runs on Spring boots framework with
 *          provided annotation and technologies.
 * 
 *          This is server side application provides api like Adding new student
 *          details, update student details, overview of all students details
 */
@SpringBootApplication()
@EntityScan("com.students.entity")
@EnableJpaRepositories("com.students.dao")
public class StudentManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementAppApplication.class, args);
	}

}
