package com.students;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Shakti Shekhawat
 * @version 1.0 
 * Handles error with message and time stamp
 */
@Data
@AllArgsConstructor
public class ErrorDetails {

    private Date timestamp;

    private String message;

}
