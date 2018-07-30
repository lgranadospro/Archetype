package es.profile.phone.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import es.profile.phone.domain.User;
import es.profile.phone.domain.exception.UserNotFoundException;


/**
 * Clase para la validación de usuarios.
 */
@Service("userValidator")
public class UserValidator {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * Check create user.
	 *
	 * @param user the user
	 */
	public void checkCreateUser(User user) {
		logger.debug("Validating user: {}", user);
		Assert.notNull(user, "User must not be null!");
		
		//TODO validaciones de servicio
		
	}
	
	/**
	 * Check update user.
	 *
	 * @param id the id
	 * @param user the user
	 */
	public void checkUpdateUser(long id, User user) {
		if (user == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			throw new UserNotFoundException(id);
		}
	}

	/**
	 * Check delete user.
	 *
	 * @param id the id
	 * @param user the user
	 */
	public void checkDeleteUser(long id, User user) {
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			throw new UserNotFoundException(id);
		}
		
	}

}
