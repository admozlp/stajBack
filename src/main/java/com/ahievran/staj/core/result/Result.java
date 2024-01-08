package com.ahievran.staj.core.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
	private boolean success;
	
	private String message;
	
	private int code;

	public Result(boolean success) {
		this.success = success;
	}
	
	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result(boolean success, String message, int code) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
	}

}
