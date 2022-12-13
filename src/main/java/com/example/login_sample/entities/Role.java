package com.example.login_sample.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

//Entidade 'roles' do banco de dados
//Responsável por guardar os diferentes tipos de papeis de usuário.

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<Account> accounts = new ArrayList<>();
	
	public Role() {
		
	}

	public Role(long id, String name, List<Account> accounts) {
		super();
		this.id = id;
		this.name = name;
		this.accounts = accounts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
