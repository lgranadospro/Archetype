package es.profile.parameter.service.impl;


import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.profile.parameter.domain.Parameter;
import es.profile.parameter.repository.ParameterRepository;
import es.profile.parameter.service.ParameterService;

/**
 * The Class ParameterServiceImpl.
 */
@Service("parametersService")
public class ParameterServiceImpl implements ParameterService {
	

	@Autowired
	private ParameterRepository repository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	

	/* (non-Javadoc)
	 * @see es.profile.parameter.service.impl.ParameterServiceImpl#getAll()
	 */
	@Override
	public List<Parameter> getAll() {
		return repository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see es.profile.parameter.service.ParameterServiceImpl#get(string)
	 */
	@Override
	public Parameter get(String id) {
		Optional<Parameter> resultado = repository.findById(id);
		logger.warn(id);
		if(resultado.isPresent()) {
			return resultado.get();
		}
		else {
			logger.warn("El id recibido no corresponde a ningún Parameter");
			return null;
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see es.profile.parameter.service.impl.ParameterServiceImpl#postParameter(es.profile.parameter.domain.Parameter)
	 */
	@Override
	public void updateParam(Parameter param) {
		if(!param.equals(null)) {
			DateTime date = new DateTime();
			param.setCreatedDate(date);
			param.setLastModifiedDate(date);
			repository.save(param);
			
		}
		
	}
	/* (non-Javadoc)
	 * @see es.profile.parameterapi.service.impl.ParameterServiceImpl#updateUser(es.profile.parameter.domain.Parameter)
	 */
	public boolean save(Parameter param) {
		Optional<Parameter> resul =repository.findById(param.getId());
		if(resul.isPresent()) {
			DateTime date = new DateTime();
			param.setCreatedDate(resul.get().getCreatedDate());
			param.setLastModifiedDate(date);
			repository.save(param);
			return true;
		}else {
			logger.warn("El Parameter a actualizar no existe");
			return false;
		}
		

	}

	/* (non-Javadoc)
	 * @see es.profile.parameter.service.impl.ParameterServiceImpl#delete(string)
	 */
	@Override
	public boolean delete(String id) {
		if(repository.existsById(id)){
			repository.deleteById(id);
			return true;
		}
		else {
			logger.warn("El Parameter a borrar no existe");
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see es.profile.parameter.service.impl.ParameterServiceImpl#findByValue(long)
	 */
	@Override
	public List<Parameter> findByValue(long value) {
		
		return repository.findByValue(value);
	}

	/* (non-Javadoc)
	 * @see es.profile.parameter.service.impl.ParameterServiceImpl#deleteAllParameters()
	 */
	@Override
	public void deleteAllParameters() {
		repository.deleteAll();
		
	}

	@Override
	public boolean isParamExist(String paramId) {
		return repository.existsById(paramId);
	}

}
