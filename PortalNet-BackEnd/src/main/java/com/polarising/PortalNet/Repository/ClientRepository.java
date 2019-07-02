package com.polarising.PortalNet.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polarising.PortalNet.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
	
	List<Client> findByName(String name);
	
	Boolean existsByName(String name);
	
	List<Client> findByPostalCode(int postalCode);
	

}