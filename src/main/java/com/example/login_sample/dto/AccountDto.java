package com.example.login_sample.dto;

public class AccountDto {
	
	// Esta classe serve para troca de informações entre as partes do sistema
	// sem comprometer o encapsulamento das entidades.
	// O DTO significa Data Transfer Object (Objeto de Transferência de Dados)
	
	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String password;
	
	public AccountDto() {
		
	}
	
	public AccountDto(Long id, String name, String email, String cpf, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
