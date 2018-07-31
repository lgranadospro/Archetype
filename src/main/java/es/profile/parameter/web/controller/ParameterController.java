package es.profile.parameter.web.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.profile.parameter.domain.Parameter;
import es.profile.parameter.domain.User;
import es.profile.parameter.service.ParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/parameters")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Parameter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ParameterController {

	/** The parameter service. */
	@Autowired
	ParameterService paramService;
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// -------------------Retrieve All
	// Parameters---------------------------------------------

	/**
	 * List all parameters.
	 *
	 * @return the collection
	 */
	@ApiOperation(value = "Lista todos los parameter", notes = "Devuelve una lista con todos los parameter", response = Parameter[].class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Parameter[].class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Parameter not found") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Parameter> listAllParameters() {

		logger.info("Petición de lectura de todos los Parameter recibida");

		return  paramService.getAll();
	}
	/**
	 * List parameters with that value
	 * @param value the value
	 * @return the list
	 */
	@ApiOperation(value = "Lista todos los parameters con ese value", notes = "Busca un parameter por value", response = Parameter.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = User[].class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden") })
	@GetMapping(value="/value" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Parameter> searchValue(@RequestParam("value") long value) {

		logger.info("Petición de lectura de todos los Parameter recibida");
		return paramService.findByValue(value);
	}

	// -------------------Retrieve Single
	// Parameter------------------------------------------

	/**
	 * Gets the parameter.
	 *
	 * @param id
	 *            the id
	 * @return the parameter
	 */
	@ApiOperation(value = "Obtiene un parameter por id", notes = "Devuelve un parameter", response = Parameter.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = User.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "User not found") })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Parameter get(@PathVariable("id") String id) {
		logger.info("Fetching User with id {}", id);
		return paramService.get(id);
	}

	// -------------------Create a
	// Parameter-------------------------------------------

	/**
	 * Creates the parameter.
	 *
	 * @param param
	 *            the parameter
	 * @param ucBuilder
	 *            the uc builder
	 * @return the response entity
	 */
	@ApiOperation(value = "Crea un nuevo parameter", notes = "El nuevo id de parameter se asigna automáticamente. Devuelve en la cabecera la url para consultar el parameter creado", response = void.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 409, message = "Conflict") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> createParameter(@Valid @RequestBody Parameter param, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Parameter : {}", param);
		paramService.save(param);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/parameter/{id}").buildAndExpand(param.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Parameter
	// ------------------------------------------------

	/**
	 * Update parameter.
	 *
	 * @param id
	 *            the id
	 * @param param
	 *            the parameter
	 * @return the response entity
	 */
	@ApiOperation(value = "Actualiza un nuevo", notes = "No se modifica el id de parameter. Devuelve el parameter modificado", response = Parameter.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content. Parameter updated"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Parameter not found") })
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> updateParameter(UriComponentsBuilder ucBuilder,@Valid @RequestBody Parameter param) {
		logger.info("Updating Parameter with id {}", param.getId());
		paramService.updateParam(param);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/parameter/{id}").buildAndExpand(param.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete a
	// Parameter-----------------------------------------

	/**
	 * Delete parameter.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@ApiOperation(value = "Elimina un parameter por id", notes = "Elimina un parameter por id", response = void.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content. Parameter deleted"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Parameter not found") })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteParameter(@PathVariable("id") String id) {
		logger.info("Fetching & Deleting Parameter with id {}", id);
		paramService.delete(id);
		
	}

	// ------------------- Delete All Parameters-----------------------------

		/**
		 * Delete all parameters.
		 *
		 * @return the response entity
		 */
		@ApiOperation(value = "Elimina todos los parameters", notes = "Elimina todos los parameters", response = void.class)
		@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content. Parameters deleted"),
				@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden") })
		@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteAllParameters() {
			logger.info("Deleting All Parameters");
			paramService.deleteAllParameters();
		}

}