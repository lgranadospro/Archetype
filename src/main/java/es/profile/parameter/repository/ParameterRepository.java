package es.profile.parameter.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.profile.parameter.domain.Parameter;

/**
 * The Interface ParameterRepository.
 */
@Repository
public interface ParameterRepository extends MongoRepository<Parameter, String> {

	
	/**
	 * Find by value. The value must be equal.
	 *
	 * @param value
	 *            the value
	 * @return the list
	 */
	List<Parameter> findByValue(long value);

}
