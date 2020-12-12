package com.brasilprev.ClientAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brasilprev.ClientAPI.model.Client;

public interface ClientRepository extends JpaRepository<Client, String>{
	List<Client> findByNameContainingIgnoreCase(String name);
}
