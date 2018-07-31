package es.profile.parameter.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ParameterNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParameterNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1280799219620983388L;

	/**
	 * Instantiates a new parameter not found exception.
	 */
	public ParameterNotFoundException() {
		super();
	}

	/**
	 * Instantiates a new parameter not found exception.
	 *
	 * @param message
	 *            the message
	 */
	public ParameterNotFoundException(long idUser) {
		super(generateMessageException(idUser));
	}

	/**
	 * Instantiates a new parameter not found exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ParameterNotFoundException(long idUser, Throwable cause) {
		super(generateMessageException(idUser), cause);
	}

	/**
	 * Generate message exception.
	 *
	 * @param idParam
	 *            the id parameter
	 * @return the string
	 */
	private static String generateMessageException(long idParam) {
		return new StringBuilder("Parameter with id: ").append(idParam).append(" not exist.").toString();
	}
}