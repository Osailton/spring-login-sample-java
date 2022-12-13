package com.example.login_sample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login_sample.entities.Role;

//Interface que define os métodos utilizados para consulta em repositório
//da entidade Role. Extende JpaRepository, aproventando seus métodos

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
