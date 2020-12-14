package com.brasilprev.ClientAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.brasilprev.ClientAPI.model.Address;
import com.brasilprev.ClientAPI.model.Client;
import com.brasilprev.ClientAPI.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> getAllClients(){
		List<Client> clients = clientRepository.findAll();
		return clients;
	}
	
	public Client getClientByCpf(String cpf) {
		Optional<Client> clientData = clientRepository.findById(cpf);
		
		if(clientData.isPresent()) {
			return clientData.get();
		} else {
			return null;
		}
		
	}
	
	public List<Client> getClientByName(String name) {
		List<Client> clientData = clientRepository.findByNameContainingIgnoreCase(name);
		
		return clientData;
	}
	
	public Client registerClient(Client client) {
		Client createdClient = null;
		
		//If you already have a client with that cpf, do not register 
		if(getClientByCpf(client.getCpf()) == null) {
			
			if(client.getAddress() != null)
				client.getAddress().setClient(client);
			
			createdClient = clientRepository.save(client);			
		}
		
		return createdClient;
	}
	
	public boolean deleteClient(String cpf) throws EmptyResultDataAccessException{
		boolean founded = true;
		
		try {
			clientRepository.deleteById(cpf);
		} catch (EmptyResultDataAccessException e) {
			founded = false;
		}
		
		return founded;
	}
	
	public Client updateClient(Client client) {
		Optional<Client> clientData = clientRepository.findById(client.getCpf());
		
		if(clientData.isPresent()) {
			
			if(client.getAddress() != null)
				client.getAddress().setClient(client);
			
			clientRepository.save(client);
			
			return client;
		} else {
			return null;
		}
		
	}
	
	public Client updateClientAddress(Address address, String cpf) {
		Optional<Client> clientData = clientRepository.findById(cpf);
		
		if(clientData.isPresent()) {
			
			Client client = clientData.get();
			client.setAddress(address);
			
			if(client.getAddress() != null)
				client.getAddress().setClient(client);

			clientRepository.save(client);
			
			return client;
		} else {
			return null;
		}
		
	}
	
	
}
