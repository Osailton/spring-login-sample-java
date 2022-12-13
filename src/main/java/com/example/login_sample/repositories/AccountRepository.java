package com.example.login_sample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login_sample.entities.Account;

//Interface que define os métodos utilizados para consulta em repositório
//da entidade Account. Extende JpaRepository, aproventando seus métodos

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByCpf(String cpf);

}
