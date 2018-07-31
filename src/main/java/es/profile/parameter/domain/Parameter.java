package es.profile.parameter.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "parameters")
public class Parameter {
	
	@Id
	@ApiModelProperty(value = "Id del parameter")
	private String id;
	@ApiModelProperty(value = "Valor del parameter")
	private long value;
	@ApiModelProperty(value = "Application del parameter")
	private String application;
	@ApiModelProperty(value = "Valor initial del parameter")
	private String initial;
	@ApiModelProperty(value = "Descripción")
	private String description;

	
	@ApiModelProperty(value = "Usuario que ha creado el parameter")
	private String createdBy;
	@ApiModelProperty(value = "Fecha de creación")
	private DateTime createdDate;
	@ApiModelProperty(value = "Último usuario en modificar el parameter")
	private String lastModifiedBy;
	@ApiModelProperty(value = "Fecha última modificación")
	private DateTime lastModifiedDate;

}
