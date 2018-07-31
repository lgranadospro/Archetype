package es.profile.parameter.domain.exception;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonFormat;

import es.profile.parameter.util.Constantes;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase genérica para errores
 *
 * @param status
 *            the status
 * @param message
 *            the message
 * @param errors
 *            the errors
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericError {

	/** The message. */
	private String message;

	/** The cause. */
	private String errorCause;

	/** The errors. */
	private Collection<ServiceValidationError> serviceValidationErrors = new LinkedList<>();

	/** The date. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constantes.FECHA_DEFECTO)
	private Date date;

}