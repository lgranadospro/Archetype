package es.profile.phone.service;


import java.util.Collection;
import es.profile.phone.domain.User;
import java.util.List;




/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	User findById(long id);
	
	/**
	 * Find by name. The name must be equal.
	 *
	 * @param name the name
	 * @return the user
	 */
	Collection<User> findByName(String name);
	
	/**
	 * Find by name custom.
	 *
	 * @param name the name
	 * @return the collection
	 */
	Collection<User> findByNameCustom(String name);
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 */
	void save(User user);
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 */
	void updateUser(User user);
	
	/**
	 * Delete user by id.
	 *
	 * @param id the id
	 */
	void deleteUserById(long id);

	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	List<User> findAllUsers();
	
	/**
	 * Delete all users.
	 */
	void deleteAllUsers();
	
	/**
	 * Checks if is user exist.
	 *
	 * @param userId the user id
	 * @return true, if is user exist
	 */
	boolean isUserExist(Long userId);
	
}
