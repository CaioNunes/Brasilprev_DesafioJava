package com.brasilprev.ClientAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Client registerClient(Client client) {
		Client createdClient = null;
		
		//If you already have a cliente with that cpf, do not register 
		if(getClientByCpf(client.getCpf()) == null) {
			createdClient = clientRepository.save(client);			
		}
		
		return createdClient;
	}
	
}
