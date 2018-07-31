package es.profile.parameter.validator;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import es.profile.parameter.domain.Parameter;
import es.profile.parameter.domain.exception.ParameterNotFoundException;

/**
 * Clase para la validación de parameters.
 */
@Service("parameterValidator")
public class ParameterValidator {

	/** The logger. */
	private static Logger logger = LogManager.getLogger();

	/**
	 * Check create parameter.
	 *
	 * @param param
	 *            the parameter
	 */
	public void checkCreateParameter(Parameter param) {
		logger.debug("Validating parameter: {}", param);
		Assert.notNull(param, "Parameter must not be null!");

		// TODO validaciones de servicio

	}

	/**
	 * Check update parameter.
	 *
	 * @param id
	 *            the id
	 * @param user
	 *            the user
	 */
	public void checkUpdateParameter(long id, Parameter param) {
		if (param == null) {
			logger.error("Unable to update. Parameter with id {} not found.", id);
			throw new ParameterNotFoundException(id);
		}
	}

	/**
	 * Check delete parameter.
	 *
	 * @param id
	 *            the id
	 * @param param
	 *            the parameter
	 */
	public void checkDeleteParameter(long id, Parameter param) {
		if (param == null) {
			logger.error("Unable to delete. Parameter with id {} not found.", id);
			throw new ParameterNotFoundException(id);
		}

	}

}
