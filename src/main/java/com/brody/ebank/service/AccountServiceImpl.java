package com.brody.ebank.service;

/**
 * @author brody gaudel brodymounanga
 * This class contains the implementation of the various functionalities
 *  of the bank account management service.
 */
import java.util.List;

import org.springframework.stereotype.Service;

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.entities.Account;
import com.brody.ebank.enums.Status;
import com.brody.ebank.exception.AccountNotFoundException;
import com.brody.ebank.mappers.Mappers;
import com.brody.ebank.repositories.AccountRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private static final String ACCOUNT_WITH_ID = "ACCOUNT WITH ID :";
	private static final String NOT_FOUND = "NOT FOUND";
	
	private AccountRepository accountRepository;
	private GenerateRibService generateRibService;
	private Mappers mappers;
	
	

	public AccountServiceImpl(AccountRepository accountRepository, GenerateRibService generateRibService,
			Mappers mappers) {
		this.accountRepository = accountRepository;
		this.generateRibService = generateRibService;
		this.mappers = mappers;
	}

	@Override
	public AccountDTO save(AccountDTO accountDTO) {
		Account account = mappers.fromAccountDTO(accountDTO);
		account.setRib(generateRibService.generate());
		Account accountSaved = accountRepository.save(account);
		return mappers.fromAccount(accountSaved);
	}

	@Override
	public AccountDTO update(Long id, AccountDTO accountDTO) throws AccountNotFoundException {

		Account account = accountRepository.findById(id)
				.orElseThrow( ()-> new AccountNotFoundException(ACCOUNT_WITH_ID+id+NOT_FOUND));
		account.setBalance(accountDTO.getBalance());
		account.setStatus(accountDTO.getStatus());
	
		Account accountUpdated = accountRepository.save(account);
		return mappers.fromAccount(accountUpdated);
	}

	@Override
	public AccountDTO findById(Long id) throws AccountNotFoundException {
		Account account = accountRepository.findById(id)
				.orElseThrow( ()-> new AccountNotFoundException(ACCOUNT_WITH_ID+id+NOT_FOUND));
		return mappers.fromAccount(account);
	}

	@Override
	public AccountDTO findByRib(String rib) {
		Account account = accountRepository.findByRib(rib);
		return mappers.fromAccount(account);
	}

	@Override
	public List<AccountDTO> findAll() {
		List<Account> accounts = accountRepository.findAll();
		
		return accounts.stream()
				.map(account -> mappers.fromAccount(account))
				.toList();	
	}

	@Override
	public void deleteById(Long id) {
		accountRepository.deleteById(id);
	}
	
	/**
	 * if status equals -1 : desactivate bank account
	 * if status equals 0 : activate bank account
	 * if status equals 1 : suspend bank account
	 */
	@Override
	public AccountDTO updateStatus(Long id,int status) throws AccountNotFoundException {
		Account account = accountRepository.findById(id)
				.orElseThrow( ()-> new AccountNotFoundException(ACCOUNT_WITH_ID+id+NOT_FOUND));
		if(status==-1) {
			account.setStatus(Status.CREATED);
		}else if(status==0) {
			account.setStatus(Status.ACTIVATED);
		}else if(status==1){
			account.setStatus(Status.SUSPENDED);
		}
		Account accountUpdated = accountRepository.save(account);
		return mappers.fromAccount(accountUpdated);
	}

}
