package com.brody.ebank.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brody.ebank.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	Page<Operation> findByAccountIdOrderByDateDesc(Long accountId, Pageable pageable);
	
	@Query(
			  value = "SELECT * FROM operation WHERE account_id = ?1", 
			  nativeQuery = true)
	List<Operation> findByAccountId(Long id);
}
