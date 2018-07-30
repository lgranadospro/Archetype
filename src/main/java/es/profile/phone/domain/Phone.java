package es.profile.phone.domain;

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
@Document(collection = "phones")
public class Phone {
	@Id
	@ApiModelProperty(value = "Id del teléfono")
	private String id;
	@ApiModelProperty(value = "Nombre")
	private String name;
	@ApiModelProperty(value = "Número de mobileExt")
	private long mobileExt;
	@ApiModelProperty(value = "Número de landlineExt")
	private long landlineExt;
	@ApiModelProperty(value = "Número de mobilePhonee")
	private long mobilePhone;
	@ApiModelProperty(value = "Usuario que a creado el teléfono")
	private String createdBy;
	@ApiModelProperty(value = "Fecha de creación")
	private DateTime createdDate;
	@ApiModelProperty(value = "Último usuario en modificar el teléfono")
	private String lastModifiedBy;
	@ApiModelProperty(value = "Fecha última modificación")
	private DateTime lastModifiedDate;
	
}
