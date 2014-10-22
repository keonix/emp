package com.academysmart.exception;

import javax.swing.JOptionPane;

public class IncorrectEmailException extends ServletException {





	public IncorrectEmailException(String mess) {
		super(mess);

	}

	private static final long serialVersionUID = 2648198043456644144L;
}
