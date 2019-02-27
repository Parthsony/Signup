package org.parthinfotech.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ArgumentErrorsResponse {

	private HttpStatus status;
	private List<String> errors;
	private String message;

	public ArgumentErrorsResponse(HttpStatus status, List<String> errors, String message) {
		super();
		this.status = status;
		this.errors = errors;
		this.message = message;
	}

	public ArgumentErrorsResponse(HttpStatus badRequest, String error, String localizedMessage) {
		super();
		this.status = badRequest;
		this.errors = Arrays.asList(error);
		this.message = localizedMessage;
	}

}
