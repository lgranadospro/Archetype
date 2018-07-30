package  es.profile.phone.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class UserNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1280799219620983388L;

	/**
	 * Instantiates a new user not found exception.
	 */
	public UserNotFoundException() {
		super();
	}
	
	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param message the message
	 */
	public UserNotFoundException(long idUser) {
		super(generateMessageException(idUser));
	}
	
	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public UserNotFoundException(long idUser, Throwable cause) {
		super(generateMessageException(idUser), cause);
	}
	
	/**
	 * Generate message exception.
	 *
	 * @param idUser the id user
	 * @return the string
	 */
	private static String generateMessageException(long idUser) {
		return new StringBuilder("User with id: ").append(idUser).append(" not exist.").toString();
	}
}