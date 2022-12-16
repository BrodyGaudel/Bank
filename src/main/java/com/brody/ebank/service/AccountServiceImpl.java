/**
 * @author brody gaudel brodymounanga
 * This class contains the implementation of the various functionalities
 *  of the bank account management service.
 */

package com.brody.ebank.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

	/**
	 * Sa an account
	 * @param accountDTO request
	 * @return accountDTO saved response
	 */
	@Override
	public AccountDTO save(AccountDTO accountDTO) {
		log.info("In save()");
		Account account = mappers.fromAccountDTO(accountDTO);
		account.setRib(generateRibService.generate());
		Account accountSaved = accountRepository.save(account);
		log.info("Account Saved");
		return mappers.fromAccount(accountSaved);
	}

	/**
	 * Update an account
	 * @param id type Long
	 * @param accountDTO request
	 * @return accountDTO updated response
	 * @throws AccountNotFoundException Account Not Found
	 */
	@Override
	public AccountDTO update(Long id, AccountDTO accountDTO) throws AccountNotFoundException {
		log.info("In update()");
		Account account = accountRepository.findById(id)
				.orElseThrow( ()-> new AccountNotFoundException(ACCOUNT_WITH_ID+id+NOT_FOUND));
		account.setBalance(accountDTO.getBalance());
		account.setStatus(accountDTO.getStatus());
	
		Account accountUpdated = accountRepository.save(account);
		log.info("Account updated");
		return mappers.fromAccount(accountUpdated);
	}

	/**
	 * return an account by its id
	 * @param id type Long
	 * @return AccountDTO response
	 * @throws AccountNotFoundException Account Not Found
	 */
	@Override
	public AccountDTO findById(Long id) throws AccountNotFoundException {
		log.info("In findById()");
		Account account = accountRepository.findById(id)
				.orElseThrow( ()-> new AccountNotFoundException(ACCOUNT_WITH_ID+id+NOT_FOUND));
		log.info("Account found");
		return mappers.fromAccount(account);
	}

	/**
	 * return an account by its rib
	 * @param rib type String
	 * @return AccountDTO created
	 */
	@Override
	public AccountDTO findByRib(String rib) {
		log.info("In findByRib()");
		Account account = accountRepository.findByRib(rib);
		log.info("Account found");
		return mappers.fromAccount(account);
	}

	/**
	 * return list of accounts
	 * @return List<AccountDTO>
	 */
	@Override
	public List<AccountDTO> findAll() {
		log.info("In findAll()");
		List<Account> accounts = accountRepository.findAll();

		log.info("Account(s) found");
		return accounts.stream()
				.map(account -> mappers.fromAccount(account))
				.toList();	
	}

	/**
	 * delete account by id
	 * @param id type Long
	 */
	@Override
	public void deleteById(Long id) {
		log.info("In deleteById()");
		accountRepository.deleteById(id);
		log.info("Account deleted");
	}
	

	/**
	 *
	 * @param id type Long
	 * @param status if status equals -1 : desactivate bank account
	 * 	 * if status equals 0 : activate bank account
	 * 	 * if status equals 1 : suspend bank account
	 * @return AccountDTO Updated
	 * @throws AccountNotFoundException Account that you want to update not found
	 */
	@Override
	public AccountDTO updateStatus(Long id,int status) throws AccountNotFoundException {
		log.info("In updateStatus()");
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
		log.info("Account Status Updated");
		return mappers.fromAccount(accountUpdated);
	}

}
