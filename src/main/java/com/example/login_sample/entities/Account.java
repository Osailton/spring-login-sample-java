package com.example.login_sample.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

// Entidade 'accounts' do banco de dados
// Responsável por guardar os usuários do sistema.

@Entity
@Table(name = "accounts")
public class Account {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(name = "email_address", nullable = false)
	private String email;
	
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
		name = "accounts_roles",
		joinColumns = { @JoinColumn( name = "account_id", referencedColumnName = "id") },
		inverseJoinColumns = { @JoinColumn( name = "role_id", referencedColumnName = "id") }
	)
	
	private List<Role> roles = new ArrayList<>();
	
	public Account() {
		
	}

	public Account(long id, String name, String email, String cpf, String password, List<Role> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.password = password;
		this.roles = roles;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
