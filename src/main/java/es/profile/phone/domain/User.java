package es.profile.phone.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class User.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

	/** The id. */
	@ApiModelProperty(value = "Identificador único del usuario")
	private long id;

	/** The name. */
	@ApiModelProperty(value = "Nombre del usuario")
	@NotNull(message = "Se debe informar el nombre el usuario")
	private String name;

	/** The age. */
	@ApiModelProperty(value = "Edad del usuario")
	@NotNull(message = "Se debe informar la edad el usuario")
	@Min(message = "La edad debe ser mayor que 0", value = 0)
	private int age;

	/** The salary. */
	@ApiModelProperty(value = "Salario del usuario")
	@NotNull(message = "Se debe informar el salario del usuario")
	@Min(message = "El salario debe ser mayor que 0", value = 0)
	private double salary;

}
