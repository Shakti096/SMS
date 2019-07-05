package com.students.converter;

import com.students.dto.AddressDTO;
import com.students.dto.StudentDTO;
import com.students.entity.Student;

public class StudentDTOConverter {

    public static StudentDTO convertStudent(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmailId());
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(student.getAddress().getId());
        addressDTO.setAddressLine1(student.getAddress().getAddressLine1());
        addressDTO.setAddressLine2(student.getAddress().getAddressLine2());
        addressDTO.setCity(student.getAddress().getCity());
        addressDTO.setCountry(student.getAddress().getCountry());
        addressDTO.setPostalCode(student.getAddress().getPostalCode());
        studentDTO.setAddress(addressDTO);
        return studentDTO;
    }

}
