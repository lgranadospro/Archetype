package es.profile.phone.repository;

import java.util.Collection;
import es.profile.phone.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;



/**
 * The Interface UserRepository.
 */
public interface UserRepository extends MongoRepository<User, Long> {

	/**
	 * Find by name. The name must be equal.
	 *
	 * @param name the name
	 * @return the collection
	 */
	Collection<User> findByName(String name);
	
}
