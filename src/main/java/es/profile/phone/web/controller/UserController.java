package es.profile.phone.web.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import es.profile.phone.domain.User;
import es.profile.phone.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * The Class UserController.
 *
 * @author Jose A. Braojos <jabraojos@profile.es>
 *
 */

@RestController
@RequestMapping("/api/user")
@Api(tags="Users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger();

	/** The user service. */
	@Autowired
	private UserService userService;
	
	// -------------------Retrieve All Users---------------------------------------------

	/**
	 * List all users.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "Lista todos los usuarios", notes = "Devuelve una lista con todos los usuarios", response = User[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = User[].class),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "User not found")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Collection<User> listAllUsers() {
		logger.info("Fetching all users");
		return userService.findAllUsers();
	}

	// -------------------Retrieve Single User------------------------------------------

	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@ApiOperation(value = "Obtiene un usuario por id", notes = "Devuelve un usuario", response = User.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = User.class),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "User not found")
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		return userService.findById(id);
	}
	
	/**
	 * Gets the users by name.
	 *
	 * @param name the name
	 * @return the users by name
	 */
	@ApiOperation(value = "Lista los usuarios por nombre. El nombre debe ser exactamente igual", notes = "Devuelve una lista con todos los usuarios que tienen un nombre", response = User[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = User[].class),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden")
	})
	@GetMapping(value = "/byName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Collection<User> getUsersByName(@PathVariable("name") String name) {
		logger.info("Fetching User with name {}", name);
		return userService.findByName(name);
	}
	
	/**
	 * Find by name custom.
	 *
	 * @param name the name
	 * @return the collection
	 */
	@ApiOperation(value = "Lista los usuarios por nombre. Busqueda avanzada sin mayúsculas y sin coincidencia exacta", notes = "Devuelve una lista con todos los usuarios que tienen un nombre", response = User[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = User[].class),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden")
	})
	@GetMapping(value = "/byNameCustom/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Collection<User> findByNameCustom(@PathVariable("name") String name) {
		logger.info("Fetching User with name {}", name);
		return userService.findByNameCustom(name);
	}

	// -------------------Create a User-------------------------------------------

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @param ucBuilder the uc builder
	 * @return the response entity
	 */
	@ApiOperation(value = "Crea un nuevo usuario", notes = "El nuevo id de usuario se asigna automáticamente. Devuelve en la cabecera la url para consultar el usaurio creado", response = void.class)
	@ApiResponses(value = { 
	        @ApiResponse(code = 201, message = "Created"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"), 
	        @ApiResponse(code = 409, message = "Conflict")})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		userService.save(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	/**
	 * Update user.
	 *
	 * @param id the id
	 * @param user the user
	 * @param ucBuilder the uc builder
	 * @return the response entity
	 */
	@ApiOperation(value = "Actualiza un usaurio", notes = "No se modifica el id de usuario.", response = Void.class)
	@ApiResponses(value = { 
	        @ApiResponse(code = 204, message = "No Content. User updated"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "User not found")
	})
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> updateUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Updating User with id {}", user.getId());

		userService.updateUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete a User-----------------------------------------

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@ApiOperation(value = "Elimina un usuario por id", notes = "Elimina un usuario por id", response = void.class)
	@ApiResponses(value = { 
	        @ApiResponse(code = 204, message = "No Content. User deleted"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "User not found")
	})
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);
		userService.deleteUserById(id);
	}

	// ------------------- Delete All Users-----------------------------

	/**
	 * Delete all users.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "Elimina todos los usuario", notes = "Elimina todos los usuarios", response = void.class)
	@ApiResponses(value = { 
	        @ApiResponse(code = 204, message = "No Content. Users deleted"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Forbidden")
	})
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllUsers() {
		logger.info("Deleting All Users");
		userService.deleteAllUsers();
	}

}