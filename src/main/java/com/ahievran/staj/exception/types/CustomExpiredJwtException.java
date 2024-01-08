package com.ahievran.staj.exception.types;
public class CustomExpiredJwtException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomExpiredJwtException(String message) {
		super(message);
	}
}
