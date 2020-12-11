package com.brasilprev.ClientAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brasilprev.ClientAPI.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
}
