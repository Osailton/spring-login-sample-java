package com.example.login_sample.services.implementations;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.login_sample.entities.Account;
import com.example.login_sample.entities.Role;
import com.example.login_sample.repositories.AccountRepository;

@Service
public class CustomAccountService implements UserDetailsService {
	
	private AccountRepository accountRepository;
	
	public CustomAccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	// Reescreve o carregamento de usuário do Spring Security, buscando
	// por CPF, o campo único utilizado nessa aplicação como username

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Account account = this.accountRepository.findByCpf(cpf);
		if(account != null) {
			return new User(account.getCpf(),
					account.getPassword(),
					mapRolesToAuthorities(account.getRoles()));
		} else {
			throw new UsernameNotFoundException("O CPF ou senha informados são inválidos");
		}
	}
	
	// Transforma a lista de papéis (lista da entidade Role) na
	// collection requerida pelo User do Spring Security
	
	private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }

}
