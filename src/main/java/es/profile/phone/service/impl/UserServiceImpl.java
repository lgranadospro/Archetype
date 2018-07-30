package es.profile.phone.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import es.profile.phone.domain.User;
import es.profile.phone.domain.exception.UserNotFoundException;
import es.profile.phone.repository.UserRepository;
import es.profile.phone.service.UserService;
import es.profile.phone.validator.UserValidator;


/**
 * The Class UserServiceImpl.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	

	/** The user dao. */
	@Autowired
	private UserRepository userRepository;
	
	/** The user validator. */
	@Autowired
	private UserValidator userValidator;
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User findById(long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException(id);
		}
		
	}
	
	@Override
	public Collection<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	
	@Override
	public Collection<User> findByNameCustom(String name) {
		User exampleUser = new User();
		exampleUser.setName(name);

		Example<User> example = Example.of(exampleUser, ExampleMatcher
				.matchingAny()
				//Para que solo busque por nombre
				.withIgnorePaths("id", "age", "salary")
				.withIgnoreCase()
				);
		return userRepository.findAll(example);
	}
	
	@Override
	public void save(User user) {
		userValidator.checkCreateUser(user);
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		User currentUser = findById(user.getId());
		userValidator.checkUpdateUser(user.getId(), currentUser);

		BeanUtils.copyProperties(user, currentUser);
		
		userRepository.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		User user = findById(id);
		userValidator.checkDeleteUser(id, user);
		userRepository.deleteById(id);
	}

	@Override
	public boolean isUserExist(Long idUser) {
		return userRepository.existsById(idUser);
	}
	
	@Override
	public void deleteAllUsers(){
		userRepository.deleteAll();
	}

}
