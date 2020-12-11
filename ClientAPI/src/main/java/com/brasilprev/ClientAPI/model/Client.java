package com.brasilprev.ClientAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client {
	
	@Id
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name="CPF", nullable = true)
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
