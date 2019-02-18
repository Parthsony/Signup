package org.parthinfotech.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SignupExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleUserAlreadyExistException(Exception ue, WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(new Date(), ue.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
}
