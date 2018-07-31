package es.profile.parameter.service;

import java.util.List;

import es.profile.parameter.domain.Parameter;

/**
 * The Interface ParameterService.
 */
public interface ParameterService {

	
	/**
	 * Find all parameters.
	 *
	 * @return the list
	 */
	List<Parameter> getAll();
	
	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the parameter
	 */
	Parameter get(String id);
	
	
	/**
	 * Update parameter.
	 *
	 * @param param
	 *            the parameter
	 */
	void updateParam(Parameter param);

	/**
	 * Save parameter.
	 *
	 * @param param
	 *            the parameter
	 */
	boolean save(Parameter param);

	
	/**
	 * Delete parameter by id.
	 *
	 * @param id
	 *            the id
	 */
	boolean delete(String id);
	
	
	/**
	 * Find by value. The value must be equal.
	 *
	 * @param value
	 *            the value
	 * @return the list parameters
	 */
	List<Parameter> findByValue(long value);
	
	/**
	 * Delete all parameters.
	 */
	void deleteAllParameters();
	
	/**
	 * Checks if is parameter exist.
	 *
	 * @param paramId
	 *            the parameter id
	 * @return true, if is parameter exist
	 */
	
	boolean isParamExist(String paramId);

}
