package com.ahievran.staj.core.result;


public class DataResult<T> extends Result{
	private T data;
	
	public DataResult(T data,  boolean success) {
		super(success);
		this.data = data;
	}
	
	public DataResult(T data,  boolean success, String message) {
		super(success, message);
		this.data = data;
	}

	
	public DataResult(T data,  boolean success, String message, int code) {
		super(success, message, code);
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
