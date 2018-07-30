package es.profile.phone.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import es.profile.phone.domain.Phone;
import es.profile.phone.domain.User;

@Repository
public interface PhonesRepository extends MongoRepository<Phone,String>{
	
	Collection<User> findByName(String name);

}
