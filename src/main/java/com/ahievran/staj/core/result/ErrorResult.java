package com.ahievran.staj.core.result;
import java.util.Map;

public class ErrorResult extends Result{
	
	private Map<String, String> errorFields;
	
	public ErrorResult() {
		super(false);
	}
	
	public ErrorResult(String message) {
		super(false, message);
	}
	
	public ErrorResult(String message, int code) {
		super(false, message, code);
	}
	
	public ErrorResult(String message, int code, Map<String , String> errorFields) {
		super(false, message, code);
		this.errorFields = errorFields;
	}

	public Map<String, String> getErrorFields() {
		return errorFields;
	}

	public void setErrorFields(Map<String, String> errorFields) {
		this.errorFields = errorFields;
	}
	
}
