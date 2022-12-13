package com.example.login_sample.services;

import java.util.List;

import com.example.login_sample.dto.AccountDto;
import com.example.login_sample.entities.Account;

// Esta é a interface de serviços de Account, informando
// que métodos devem ser implementados para atender o sistema.
// Os serviços intermediam o acesso entre o controle e as entidades

public interface AccountService {
	
	void saveAccount(AccountDto accountDto);
	Account findUserByCpf(String cpf);
	List<AccountDto> findAllAccounts();

}
