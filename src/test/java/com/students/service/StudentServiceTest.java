package com.students.service;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.students.dao.StudentRepository;
import com.students.dto.AddressDTO;
import com.students.dto.StudentDTO;
import com.students.entity.Address;
import com.students.entity.Student;

/**
 * @author Shakti Shekhawat
 * @version 1.0
 * This is Test class used to test Student service methods.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
	@Mock
	private StudentRepository studentRepository;
	@InjectMocks 
	private StudentService service = new StudentService();
	
	@Test
	public void createStudentTest() throws StudentServiceException{
		StudentDTO studentDTO = new StudentDTO();
		AddressDTO addressDto = new AddressDTO();
			studentDTO.setFirstName("Shakti");
			studentDTO.setLastName("Shekhawat");
			addressDto.setAddressLine1("TESTAdd");
			addressDto.setCity("AMS");
			addressDto.setCountry("NL");
			studentDTO.setAddress(addressDto);
			service.creteStudent(studentDTO);
	}
	@Test(expected=StudentServiceException.class)
	public void createStudentTestException() throws StudentServiceException{
		StudentDTO studentDTO = new StudentDTO();
			studentDTO.setFirstName("Shakti");
			studentDTO.setLastName("Shekhawat");
			service.creteStudent(studentDTO);
	}
	
	@Test
	public void updatwStudentTestException() throws StudentServiceException{
		StudentDTO studentDTO = new StudentDTO();
			studentDTO.setFirstName("Shakti");
			studentDTO.setLastName("Shekhawat");
			studentDTO.setStudentId(1L);
			Student student = new Student();
			Address address = new Address();
			student.setId(1L);
			student.setFirstName("SS");
			student.setLastName("S");
			address.setAddressLine1("TESTAdd");
			address.setCity("AMS");
			address.setCountry("NL");
			student.setAddress(address);
			
			AddressDTO addressDto = new AddressDTO();
			addressDto.setAddressLine1("TESTAdd");
			addressDto.setCity("AMS");
			addressDto.setCountry("NL");
			studentDTO.setAddress(addressDto);
			Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
			service.updateStudentDetails(studentDTO);	}
	
}
