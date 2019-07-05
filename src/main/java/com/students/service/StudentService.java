package com.students.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.students.ErrorDetails;
import com.students.dao.StudentRepository;
import com.students.dto.StudentDTO;
import com.students.entity.Address;
import com.students.entity.AuditTrailRecord;
import com.students.entity.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Shakti Shekhawat
 * @version 1.0 StudentService contains all the method to perform services used
 *          in Stundent Management System
 *
 */
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = StudentServiceException.class)
	public void creteStudent(StudentDTO studentDTO) throws StudentServiceException {

		try {
			Student student = new Student();
			student.setFirstName(studentDTO.getFirstName());
			student.setLastName(studentDTO.getLastName());
			student.setEmailId(studentDTO.getEmail());

			// setting address details for student
			if (studentDTO.getAddress() != null) {
				Address address = new Address();
				address.setAddressLine1(studentDTO.getAddress().getAddressLine1());
				address.setAddressLine2(studentDTO.getAddress().getAddressLine2());
				address.setCity(studentDTO.getAddress().getCity());
				address.setCountry(studentDTO.getAddress().getCountry());
				address.setPostalCode(studentDTO.getAddress().getPostalCode());
				address.setStudent(student);
				student.setAddress(address);
			} else {
				ErrorDetails errorDetails = new ErrorDetails(new Date(), "Please provide student address");
				throw new StudentServiceException(errorDetails);
			}

			// setting audit trail details for student
			List<AuditTrailRecord> AuditTrailRecordList = new ArrayList<AuditTrailRecord>();
			AuditTrailRecord auditTrailRecord = new AuditTrailRecord();
			auditTrailRecord.setActionCode("CREATE");
			auditTrailRecord.setUpdatedBy("admin");
			auditTrailRecord.setUpdatedTime(new Date());
			auditTrailRecord.setDescription(
					"Student has been created : " + studentDTO.getFirstName() + " " + studentDTO.getLastName());
			auditTrailRecord.setStudent(student);
			AuditTrailRecordList.add(auditTrailRecord);
			student.setAuditTrailRecords(AuditTrailRecordList);

			studentRepository.save(student);
		} catch (HibernateException e) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "Error while saving student detail");
			throw new StudentServiceException(errorDetails);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = StudentServiceException.class)
	public Optional<Student> getStudentDetail(Long studentId) throws StudentServiceException {
		try {
			return studentRepository.findById(studentId);
		} catch (HibernateException e) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "Error while fetching student details");
			throw new StudentServiceException(errorDetails);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = StudentServiceException.class)
	public List<Student> getAllStudentDetails() throws StudentServiceException {
		try {
			return studentRepository.findAll();
		} catch (HibernateException e) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "Error while fetching students details");
			throw new StudentServiceException(errorDetails);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = StudentServiceException.class)
	public void updateStudentDetails(StudentDTO studentDTO) throws StudentServiceException {
		try {
			if (studentDTO == null || studentDTO.getStudentId() == null) {
				ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid Student ID");
				throw new StudentServiceException(errorDetails);
			}

			Optional<Student> obj = studentRepository.findById(studentDTO.getStudentId());
			if (!obj.isPresent()) {
				ErrorDetails errorDetails = new ErrorDetails(new Date(), "Student ID does not exist");
				throw new StudentServiceException(errorDetails);
			} else {
				Student studentEntity = obj.get();
				studentEntity.setFirstName(studentDTO.getFirstName());
				studentEntity.setLastName(studentDTO.getLastName());
				studentEntity.setEmailId(studentDTO.getEmail());

				// setting address details for student
				if (studentDTO.getAddress() != null) {
					studentEntity.getAddress().setAddressLine1(studentDTO.getAddress().getAddressLine1());
					studentEntity.getAddress().setAddressLine2(studentDTO.getAddress().getAddressLine2());
					studentEntity.getAddress().setCity(studentDTO.getAddress().getCity());
					studentEntity.getAddress().setCountry(studentDTO.getAddress().getCountry());
					studentEntity.getAddress().setPostalCode(studentDTO.getAddress().getPostalCode());
				} else {
					ErrorDetails errorDetails = new ErrorDetails(new Date(), "Please provide student address");
					throw new StudentServiceException(errorDetails);
				}

				// setting audit trail details for student
				List<AuditTrailRecord> AuditTrailRecordList = new ArrayList<>();
				AuditTrailRecord auditTrailRecord = new AuditTrailRecord();
				auditTrailRecord.setActionCode("UPDATE");
				auditTrailRecord.setUpdatedBy("admin");
				auditTrailRecord.setUpdatedTime(new Date());
				auditTrailRecord.setDescription("Student details has been updated : " + studentDTO.getFirstName() + " "
						+ studentDTO.getLastName());
				auditTrailRecord.setStudent(studentEntity);
				AuditTrailRecordList.add(auditTrailRecord);
				studentEntity.setAuditTrailRecords(AuditTrailRecordList);
				studentRepository.save(studentEntity);

			}

		} catch (HibernateException e) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "Error while fetching student details");
			throw new StudentServiceException(errorDetails);
		}
	}

}
