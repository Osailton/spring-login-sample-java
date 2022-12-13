package com.example.login_sample.services.implementations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.login_sample.dto.AccountDto;
import com.example.login_sample.entities.Account;
import com.example.login_sample.entities.Role;
import com.example.login_sample.repositories.AccountRepository;
import com.example.login_sample.repositories.RoleRepository;
import com.example.login_sample.services.AccountService;

// Implementação dos serviços para Account

@Service
public class AccountServiceImplementation implements AccountService{
	
	private AccountRepository accountRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder encoder;
	
	public AccountServiceImplementation(AccountRepository accountRepository, RoleRepository roleRepository,
			PasswordEncoder encoder) {
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}

	// Recebe o DTO do controle e cria um novo usuário
	
	@Override
	public void saveAccount(AccountDto accountDto) {
		Account account = new Account();
		account.setCpf(accountDto.getCpf());
		account.setName(accountDto.getName());
		account.setEmail(accountDto.getEmail());
		account.setPassword(this.encoder.encode(accountDto.getPassword()));
		
		// Por padrão, está criando todos os usuários com o papel de ADMIN
		// Para implementar o sistema na prática, com vários papéis, é preciso
		// capturar no AccountDto o papel e incluir no banco o registro de papel
		// correspondente.
		
		Role role = this.roleRepository.findByName("ROLE_ADMIN");
		if(role == null) {
			role = checkRoleExist();
		}
		
		account.setRoles(Arrays.asList(role));
		
		this.accountRepository.save(account);
	}

	@Override
	public Account findUserByCpf(String cpf) {
		return this.accountRepository.findByCpf(cpf);
	}

	@Override
	public List<AccountDto> findAllAccounts() {
		List<Account> accounts = this.accountRepository.findAll();
		return accounts.stream()
				.map((account) -> mapToAccountDto(account))
				.collect(Collectors.toList());
	}
	
	// Este método faz a conversão de uma Account (entidade) para
	// AccountDto, Data Transfer Object
	
	private AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setCpf(account.getCpf());
		accountDto.setEmail(account.getEmail());
		accountDto.setName(account.getName());
		return accountDto;
	}
	
	// Checa se existe o papel do usuário a ser incluído no
	// banco de dados, caso não exista, cria o novo tipo de papel
	
	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		return roleRepository.save(role);
	}

}
