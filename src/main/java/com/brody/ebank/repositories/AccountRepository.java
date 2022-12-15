package com.brody.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brody.ebank.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Query(
			  value = "SELECT * FROM account WHERE rib = ?1", 
			  nativeQuery = true)
	Account findByRib(String rib);
}
