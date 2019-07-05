package com.students.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Shakti Shekhawat
 * @version 1.0 Student Entity class which used for ORM with h2 database of
 *          spring boot This contains all student related personal information
 *
 */
@Entity
@Table(name = "STUDENT")
public class Student implements Serializable {

	private static final long serialVersionUID = -2585849429399769580L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	@NotNull
	@Size(min = 1, message = "First Name should have atleast 1 characters")
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	@NotNull
	@Size(min = 1, message = "Last Name should have atleast 1 characters")
	private String lastName;

	@Column(name = "EMAIL_ID")
	@NotNull
	@Email(message = "Invalid email id")
	private String emailId;

	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AuditTrailRecord> auditTrailRecords;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "id")
	private Address address;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<AuditTrailRecord> getAuditTrailRecords() {
		return auditTrailRecords;
	}

	public void setAuditTrailRecords(List<AuditTrailRecord> auditTrailRecords) {
		this.auditTrailRecords = auditTrailRecords;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
