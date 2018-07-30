package  es.profile.phone.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase para los errores de validación de servicio. Se debe de informar dentro de GenericError
 *
 * @param object the object
 * @param field the field
 * @param rejectedValue the rejected value
 * @param message the message
 */

@Data
@AllArgsConstructor
public class ServiceValidationError {
   
   /** The object. */
   private String object;
   
   /** The field. */
   private String field;
   
   /** The rejected value. */
   private Object rejectedValue;
   
   /** The message. */
   private String message;

}