package com.brasilprev.ClientAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.ClientAPI.model.Address;
import com.brasilprev.ClientAPI.model.Client;
import com.brasilprev.ClientAPI.service.ClientService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/brasilprev/api", produces = "application/json")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@ApiOperation(value = "Return the list of all clients.")
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClients(){
		try {
					
			List<Client> clients = clientService.getAllClients();
			
			if(clients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(clients, HttpStatus.OK);				
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Return a client by the specified cpf.")
	@GetMapping("/client/{cpf}")
	public ResponseEntity<Client> getClientByCpf(@PathVariable("cpf") String cpf){
		try {
					
			Client client = clientService.getClientByCpf(cpf);
			
			if(client != null) {
				return new ResponseEntity<>(client, HttpStatus.OK);				
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Return a client by the specified part of name.")
	@GetMapping("/clients/{name}")
	public ResponseEntity<List<Client>> getClientByNameContaining(@PathVariable("name") String name){
		try {
					
			List<Client> clients = clientService.getClientByName(name);
			
			if(!clients.isEmpty()) {
				return new ResponseEntity<>(clients, HttpStatus.OK);				
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Register a new client.")
	@PostMapping("/registerClient")
	public ResponseEntity<String> registerClient(@RequestBody Client client){
		try {
			Client createdClient = clientService.registerClient(client);
			
			if(createdClient != null) {
				return new ResponseEntity<>("Client successfully registered !", HttpStatus.CREATED);				
			} else {
				return new ResponseEntity<>("This CPF is already in use for a client !", HttpStatus.CONFLICT);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Delete a client by the specified cpf.")
	@DeleteMapping("/deleteClient/{cpf}")
	public ResponseEntity<String> deleteClientByCpf(@PathVariable("cpf") String cpf){
		try {
			boolean removed = clientService.deleteClient(cpf);
			
			if(removed) {
				return new ResponseEntity<>("Client successfully deleted !", HttpStatus.OK);				
			} else {
				return new ResponseEntity<>("No client found with this cpf !", HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Update a registered client.")
	@PatchMapping("/updateClient")
	public ResponseEntity<String> updateClient(@RequestBody Client client){
		try {
			Client updatedClient = clientService.updateClient(client);
			
			if(updatedClient != null) {
				return new ResponseEntity<>("Client successfully updated !", HttpStatus.OK);				
			} else {
				return new ResponseEntity<>("No client found with this cpf !", HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Update a registered client`s address.")
	@PatchMapping("/updateAddress/{cpf}")
	public ResponseEntity<String> updateAddress(@RequestBody Address clientAddress, @PathVariable("cpf") String cpf){
		try {
			Client updatedClient = clientService.updateClientAddress(clientAddress, cpf);
			
			if(updatedClient != null) {
				return new ResponseEntity<>("Client Address successfully updated !", HttpStatus.OK);				
			} else {
				return new ResponseEntity<>("No client found with this cpf !", HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
