package com.shahid.mail.response;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * A POJO containing the status of an action and a {@link List} of messages. 
 * This is mainly used as a DTO for the presentation layer
 */
public class StatusResponse {

	private Boolean success;
	private HttpStatus httpStatus;
	private List<String> message;
	
	public StatusResponse() {
		this.message = new ArrayList<String>();
	}

	public StatusResponse(Boolean success) {
		super();
		this.success = success;
		this.httpStatus = null;

	}

	public StatusResponse(Boolean success, HttpStatus httpStatus, String message) {
		super();
		this.success = success;
		this.httpStatus = httpStatus;
		this.message = new ArrayList<String>();
		this.message.add(message);
	}
	
	public StatusResponse(Boolean success, HttpStatus httpStatus, List<String> message) {
		super();
		this.success = success;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<String> getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message.add(message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String mess: message) {
			sb.append(mess +", ");
		}
		
		return "StatusResponse [success=" + success + ", httpStatus=" + httpStatus.toString() + ", message=" + sb.toString()
				+ "]";
	}
}
