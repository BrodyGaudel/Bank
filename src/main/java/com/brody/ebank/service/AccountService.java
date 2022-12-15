package com.brody.ebank.service;

import java.util.List;

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.exception.AccountNotFoundException;

public interface AccountService {
	
	AccountDTO save(AccountDTO accountDTO);
	AccountDTO update(Long id, AccountDTO accountDTO) throws AccountNotFoundException;
	AccountDTO findById(Long id) throws AccountNotFoundException;
	AccountDTO findByRib(String rib);
	List<AccountDTO> findAll();
	void deleteById(Long id);
	AccountDTO updateStatus(Long id, int status) throws AccountNotFoundException;
	
}
