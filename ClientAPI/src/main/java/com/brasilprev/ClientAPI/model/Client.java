package com.brasilprev.ClientAPI.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client implements Serializable{
	
	@Id
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}
