package es.profile.phone.web.exception;

import java.util.LinkedList;

import org.joda.time.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.profile.phone.domain.exception.GenericError;
import es.profile.phone.domain.exception.ServiceValidationError;
import es.profile.phone.domain.exception.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle all.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public GenericError handleAll(Exception ex, WebRequest request) {
		return new GenericError("Internal Server Error", ex.getLocalizedMessage(), null, DateTime.now().toDate());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		GenericError error = new GenericError("Error de validación", ex.getLocalizedMessage(), new LinkedList<>(), DateTime.now().toDate());
		for(FieldError objectError: ex.getBindingResult().getFieldErrors()) {
			ServiceValidationError serviceError = new ServiceValidationError(objectError.getObjectName(), objectError.getField(), objectError.getRejectedValue(), objectError.getDefaultMessage());
			error.getServiceValidationErrors().add(serviceError);
		}
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle user not found exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected GenericError handleUserNotFoundException(UserNotFoundException ex) {
		return new GenericError(ex.getMessage(), ex.getLocalizedMessage(), new LinkedList<>(), DateTime.now().toDate());
	}
}
