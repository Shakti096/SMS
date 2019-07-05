package com.students.service;

import com.students.ErrorDetails;

/**
 * @author Shakti Shekhawat
 * @version 1.0 StudentServiceException handles all errors occured in calling
 *          services methods.
 *
 */
public class StudentServiceException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5419862181734394937L;

	public StudentServiceException() {
		super();
	}

	public StudentServiceException(final String messages) {
		super(messages);
	}

	public StudentServiceException(ErrorDetails errorDetails) {
		super(errorDetails.getMessage());
	}

}
