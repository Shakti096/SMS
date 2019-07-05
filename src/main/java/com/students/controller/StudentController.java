package com.students.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.students.converter.StudentDTOConverter;
import com.students.dto.StudentDTO;
import com.students.entity.Student;
import com.students.service.StudentService;
import com.students.service.StudentServiceException;

/**
 * @author Shakti Shekhawat
 * @version 1.0
 * 
 **********************************************
 *          Student Management application runs on Spring boots framework with
 *          provided annotation and technologies.
 * 
 *          StudentController  provides api like Adding new student
 *          details, update student details, overview of all students details
 */
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * creteStudent provide an api to add new students details
	 * @param student
	 * @return
	 * @throws StudentServiceException
	 */
	@RequestMapping(value = "student/create", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> creteStudent(@Valid @RequestBody StudentDTO student) throws StudentServiceException {
		try {
			studentService.creteStudent(student);
		} catch (StudentServiceException e) {
			e.printStackTrace();
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body("SUCEESS");
	}

	/**
	 * getStudentDetail can be use to fetch details of specific student
	 * @param studentId
	 * @return
	 * @throws StudentServiceException
	 */
	@RequestMapping(value = "student/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getStudentDetail(@PathVariable Long studentId) throws StudentServiceException {
		StudentDTO dto = null;
		try {
			Optional<Student> response = studentService.getStudentDetail(studentId);
			if (response.isPresent()) {
				dto = StudentDTOConverter.convertStudent(response.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO Data found.");
			}

		} catch (StudentServiceException e) {
			e.printStackTrace();
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	/**
	 * getStudentsDetail provides all students detail with id , name , address
	 * @return
	 * @throws StudentServiceException
	 */
	@RequestMapping(value = "students", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getStudentsDetail() throws StudentServiceException {
		List<StudentDTO> dto = new ArrayList<>();
		try {
			List<Student> response = studentService.getAllStudentDetails();
			if (response.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO Data found.");
			} else {
				response.forEach(student -> {
					StudentDTO studentDTO = StudentDTOConverter.convertStudent(student);
					dto.add(studentDTO);
				});
			}

		} catch (StudentServiceException e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	/**
	 * updateStudentDetails to update data of student using student id as input parameter
	 * @param student
	 * @return
	 * @throws StudentServiceException
	 */
	@RequestMapping(value = "student/update", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateStudentDetails(@Valid @RequestBody StudentDTO student)
			throws StudentServiceException {
		try {
			studentService.updateStudentDetails(student);

		} catch (StudentServiceException e) {
			e.printStackTrace();
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
	}
}
