package com.brody.ebank.service;

import java.util.List;

import com.brody.ebank.dto.CreditDTO;
import com.brody.ebank.dto.DebitDTO;
import com.brody.ebank.dto.HistoryDTO;
import com.brody.ebank.dto.OperationDTO;
import com.brody.ebank.dto.TransferDTO;
import com.brody.ebank.exception.AccountNotActivatedException;
import com.brody.ebank.exception.AccountNotFoundException;
import com.brody.ebank.exception.AccountSuspendedException;
import com.brody.ebank.exception.BalanceNotSufficientException;



public interface OperationService {
	
	void debit(DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException;
	
	void credit(CreditDTO creditDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException;
	
	void transfert(TransferDTO transferDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException;
	
	List<OperationDTO> findAllByAccountId(Long accountId);
	
	HistoryDTO findHistory(Long accountId, int page, int size) throws AccountNotFoundException;
	

}
