package org.parthinfotech.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SignupResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<>();

		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
		});

		ex.getBindingResult().getGlobalErrors().forEach(globalError -> {
			errors.add(globalError.getObjectName() + " :" + globalError.getDefaultMessage());
		});

		ArgumentErrorsResponse response = new ArgumentErrorsResponse(HttpStatus.BAD_REQUEST, errors,
				ex.getLocalizedMessage());

		return handleExceptionInternal(ex, response, headers, response.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";

		ArgumentErrorsResponse apiError = new ArgumentErrorsResponse(HttpStatus.BAD_REQUEST, error,
				ex.getLocalizedMessage());
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleApplicationExceptions(Exception ue, WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(new Date(), ue.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		ArgumentErrorsResponse apiError = new ArgumentErrorsResponse(HttpStatus.BAD_REQUEST, errors,
				ex.getLocalizedMessage());

		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ArgumentErrorsResponse apiError = new ArgumentErrorsResponse(HttpStatus.BAD_REQUEST, error,
				ex.getLocalizedMessage());

		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
