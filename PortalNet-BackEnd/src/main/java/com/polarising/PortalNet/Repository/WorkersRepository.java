package com.polarising.PortalNet.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polarising.PortalNet.model.Workers;

@Repository
public interface WorkersRepository extends CrudRepository<Workers, Long>{

	List<Workers> findByName(String name);
}