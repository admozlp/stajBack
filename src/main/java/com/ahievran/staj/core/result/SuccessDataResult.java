package com.ahievran.staj.core.result;
import java.io.Serializable;

public class SuccessDataResult<T> extends DataResult<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6320968626928040438L;

	public SuccessDataResult(T data) {
		super(data, true);
	}
	
	public SuccessDataResult() {
		super(null, true);
	}

	public SuccessDataResult(String message) {
		super(null, true, message);
	}
	
	public SuccessDataResult(T data, String message) {
		super(data, true, message);
	}
	
	public SuccessDataResult(T data, String message, int code) {
		super(data, true, message, code);
	}
}
