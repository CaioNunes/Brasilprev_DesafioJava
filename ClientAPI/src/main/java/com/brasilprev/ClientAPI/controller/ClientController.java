package com.brasilprev.ClientAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.ClientAPI.model.Client;
import com.brasilprev.ClientAPI.service.ClientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/brasilprev/api")
public class ClientController {
	
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClients(){
		try {
					
			List<Client> clients = clientService.getAllClients();
			return new ResponseEntity<>(clients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
