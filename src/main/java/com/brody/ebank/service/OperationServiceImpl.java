package com.brody.ebank.service;

/**
 * This class contains the implementation 
 * of functionalities related to the operations 
 * of a bank account (credit, debit, transfer)
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.dto.CreditDTO;
import com.brody.ebank.dto.DebitDTO;
import com.brody.ebank.dto.HistoryDTO;
import com.brody.ebank.dto.OperationDTO;
import com.brody.ebank.dto.TransferDTO;
import com.brody.ebank.entities.Account;
import com.brody.ebank.entities.Operation;
import com.brody.ebank.enums.Type;
import com.brody.ebank.exception.AccountNotActivatedException;
import com.brody.ebank.exception.AccountNotFoundException;
import com.brody.ebank.exception.AccountSuspendedException;
import com.brody.ebank.exception.BalanceNotSufficientException;
import com.brody.ebank.mappers.Mappers;
import com.brody.ebank.repositories.AccountRepository;
import com.brody.ebank.repositories.OperationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	
	private OperationRepository operationRepository;
	private AccountRepository accountRepository;
	private AccountService accountService;
	private Mappers mappers;
	
	
	public OperationServiceImpl(OperationRepository operationRepository,AccountRepository accountRepository,
			AccountService accountService,
			Mappers mappers) {
	
		this.operationRepository = operationRepository;
		this.accountRepository = accountRepository;
		this.accountService = accountService;
		this.mappers = mappers;
	}

	@Override
	public void debit(DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException,
			AccountSuspendedException, AccountNotActivatedException {
		
		String accountId = debitDTO.getAccountId(); 
		BigDecimal amount = debitDTO.getAmount();
		String description = debitDTO.getDescription();
		
		
		Account account = accountRepository.findByRib(accountId);
		String status = account.getStatus().toString();
		if(status.equals("SUSPENDED")) {
			String message = "ACCOUNT WITH RIB:"+account.getRib()+" IS SUSPENDED";
			throw new AccountSuspendedException(message);
		}
		
		if(status.equals("CREATED")) {
			String message = "ACCOUNT WITH RIB:"+account.getRib()+" IS NOT ACTIVATED";
			throw new AccountNotActivatedException(message);
		}
		
		if(compare(account.getBalance(), amount)==-1) {
			throw new BalanceNotSufficientException("SOLDE INSUFFISANT");
		}
		
		if(status.equals("ACTIVATED")) {
			Operation operation = new Operation();
			operation.setType(Type.DEBIT);
			operation.setAmount(amount);
			operation.setDate(new Date());
			operation.setDescription(description);
			
			operation.setAccount(account);
			
			operationRepository.save(operation);
			
			BigDecimal solde = account.getBalance().subtract(amount);
			account.setBalance(solde);
			accountRepository.save(account);
	
		}
		
	}

	@Override
	public void credit(CreditDTO creditDTO) throws AccountNotFoundException, BalanceNotSufficientException,
			AccountSuspendedException, AccountNotActivatedException {
		
		String accountId = creditDTO.getAccountId(); 
		BigDecimal amount = creditDTO.getAmount();
		String description = creditDTO.getDescription();
		
		AccountDTO accountDTO = accountService.findByRib(accountId);
		
		String status = accountDTO.getStatus().toString();
		
		if(status.equals("SUSPENDED")) {
			String message = "ACCOUNT WITH RIB : "+accountDTO.getRib()+" IS SUSPENDED";
			throw new AccountSuspendedException(message);
		}
		
		if(status.equals("CREATED")) {
			String message = "ACCOUNT WITH RIB : "+accountDTO.getRib()+" IS NOT ACTIVATED";
			throw new AccountNotActivatedException(message);
		}
		
		if(compare(amount, BigDecimal.valueOf(0.0))==-1) {
			throw new BalanceNotSufficientException("INSUFFICIENT AMOUNT");
		}
		
		if(status.equals("ACTIVATED")) {
			Operation operation = new Operation();
			
			operation.setType(Type.CREDIT);
			operation.setAmount(amount);
			operation.setDate(new Date());
			operation.setDescription(description);
			Account account = mappers.fromAccountDTO(accountDTO);
			operation.setAccount(account);
			operationRepository.save(operation);
			
			BigDecimal solde = accountDTO.getBalance().add(amount);
			accountDTO.setBalance(solde);
			accountService.update(accountDTO.getId(), accountDTO);
		}
		
	}

	@Override
	public void transfert(TransferDTO transferDTO) throws AccountNotFoundException, BalanceNotSufficientException,
			AccountSuspendedException, AccountNotActivatedException {
		
		String accountIdDestination = transferDTO.getAccountDestination();
		String accountIdSource = transferDTO.getAccountSource();
		BigDecimal amount = transferDTO.getAmount();
		String description = transferDTO.getDescription();
		debit(new DebitDTO(accountIdSource, amount, "Transfer to "+accountIdDestination+" : "+description));
		credit(new CreditDTO(accountIdDestination, amount, "Transfer from "+accountIdSource+" : "+description));
		
	}

	@Override
	public List<OperationDTO> findAllByAccountId(Long id) {
		List<Operation> operations = operationRepository.findByAccountId(id);
		
		return operations.stream()
				.map(operation -> mappers.fromOperation(operation))
				.toList();
	}

	@Override
	public HistoryDTO findHistory(Long accountId, int page, int size) throws AccountNotFoundException {
	
		AccountDTO accountDTO = accountService.findById(accountId);
		
		Page<Operation> operations = operationRepository.findByAccountIdOrderByDateDesc(accountId, PageRequest.of(page, size));
		HistoryDTO accountHistoryDTO = new HistoryDTO();
		List<OperationDTO> operationDTOS = operations.getContent().stream().map(op -> mappers.fromOperation(op)).toList();
		accountHistoryDTO.setOperationDTOS(operationDTOS);
		accountHistoryDTO.setAccountId(accountId);
		
		accountHistoryDTO.setBalance(accountDTO.getBalance());
		accountHistoryDTO.setPageSize(size);
		accountHistoryDTO.setCurrentPage(page);
		accountHistoryDTO.setTotalPages(operations.getTotalPages());
		return accountHistoryDTO;
				
	}
	
	private int compare(BigDecimal a, BigDecimal b) {
		int res;
		res = a.compareTo(b);
		return res;
	}

}
