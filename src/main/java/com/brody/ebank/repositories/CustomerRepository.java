package com.brody.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brody.ebank.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query(
			  value = "SELECT * FROM customer WHERE contact_id = ?1", 
			  nativeQuery = true)
	Customer findByContactId(Long contactId);

	@Query(
			  value = "SELECT * FROM customer WHERE account_id = ?1", 
			  nativeQuery = true)
	Customer findByAccountId(Long constomerId);
}
