package es.profile.phone.service;

import java.util.List;

import es.profile.phone.domain.Phone;


public interface PhoneService {
	
	List<Phone> getAll();
	
	Phone get(String id);
	
	void post(Phone phone);
	
	boolean put(Phone phone);
	
	boolean delete(String id);

}
