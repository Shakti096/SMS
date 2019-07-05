/**
 * 
 */
package com.students.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Shakti Shekhawat
 * @version 1.0 
 * AddressDTO class is interface for mapping all the personal information of students
 */
public class StudentDTO {

	/**
	 * student id to use for intracting with client and server interface
	 */
	private Long studentId;
	@NotNull
	@Size(min = 1, message = "First Name should have atleast 1 characters")
	private String firstName;

	@NotNull
	@Size(min = 1, message = "Last Name should have atleast 1 characters")
	private String lastName;

	private String email;

	private AddressDTO address;

	
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	/**
	 * @return the studentId
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
