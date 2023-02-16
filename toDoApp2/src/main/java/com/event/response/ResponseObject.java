package com.event.response;

public class ResponseObject {

	private Object data;
	private int status;
	private String errorMessage;

	public ResponseObject(Object data, int status, String errorMessage) {
		this.data = data;
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public ResponseObject(String errorMessage, int status) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
