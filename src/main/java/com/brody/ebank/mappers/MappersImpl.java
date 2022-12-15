package com.brody.ebank.mappers;

/**
 * @author brody gaudel
 * This class contains the mapping functionality 
 * between entities and dtos
 */


import org.springframework.stereotype.Service;

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.dto.ContactDTO;
import com.brody.ebank.dto.CustomerDTO;
import com.brody.ebank.dto.OperationDTO;
import com.brody.ebank.entities.Account;
import com.brody.ebank.entities.Contact;
import com.brody.ebank.entities.Customer;
import com.brody.ebank.entities.Operation;

@Service
public class MappersImpl implements Mappers {

	@Override
	public Contact fromContactDTO(ContactDTO contactDTO) {
		
		try {
			Contact contact = new Contact();
			contact.setAddress(contactDTO.getAddress());
			contact.setCin(contactDTO.getCin());
			contact.setId(contactDTO.getId());
			contact.setMail(contactDTO.getMail());
			contact.setPhone(contactDTO.getPhone());
			
			return contact;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public ContactDTO fromContact(Contact contact) {
		
		try {
			ContactDTO contactDTO = new ContactDTO();
			contactDTO.setAddress(contact.getAddress());
			contactDTO.setCin(contact.getCin());
			contactDTO.setId(contact.getId());
			contactDTO.setMail(contact.getMail());
			contactDTO.setPhone(contact.getPhone());
			
			return contactDTO;
		}catch(Exception e) {
			return null;
		}
		
		
	}

	@Override
	public Account fromAccountDTO(AccountDTO accountDTO) {
		
		try {
			Account account = new Account();
			account.setBalance(accountDTO.getBalance());
			account.setId(accountDTO.getId());
			account.setRib(accountDTO.getRib());
			account.setStatus(accountDTO.getStatus());
			
			return account;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public AccountDTO fromAccount(Account account) {
		
		try {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setBalance(account.getBalance());
			accountDTO.setId(account.getId());
			accountDTO.setRib(account.getRib());
			accountDTO.setStatus(account.getStatus());
			
			return accountDTO;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public Customer fromCustomerDTO(CustomerDTO customerDTO) {
		
		try {
			Customer customer = new Customer();
			customer.setId(customerDTO.getId());
			customer.setFirstname(customerDTO.getFirstname());
			customer.setName(customerDTO.getName());
			customer.setNationality(customerDTO.getNationality());
			customer.setDateOfBirth(customerDTO.getDateOfBirth());
			customer.setPlaceOfBirth(customerDTO.getPlaceOfBirth());
			customer.setSexe(customerDTO.getSexe());
			
			Contact c = fromContactDTO(customerDTO.getContactDTO());
			customer.setContact(c);
			
			Account a = fromAccountDTO(customerDTO.getAccountDTO());
			customer.setAccount(a);
			return customer;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public CustomerDTO fromCustomer(Customer customer) {
		
		try {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setId(customer.getId());
			customerDTO.setFirstname(customer.getFirstname());
			customerDTO.setName(customer.getName());
			customerDTO.setNationality(customer.getNationality());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setPlaceOfBirth(customer.getPlaceOfBirth());
			customerDTO.setSexe(customer.getSexe());
			
			ContactDTO c = fromContact(customer.getContact());
			customerDTO.setContactDTO(c);
			
			AccountDTO a = fromAccount(customer.getAccount());
			customerDTO.setAccountDTO(a);
			return customerDTO;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public Operation fromOperationDTO(OperationDTO operationDTO) {
		try {
			Operation operation = new Operation();
			operation.setId(operationDTO.getId());
			operation.setAmount(operationDTO.getAmount());
			operation.setType(operationDTO.getType());
			operation.setDate(operationDTO.getDate());
			operation.setDescription(operationDTO.getDescription());
			
			Account account = new Account();
			account.setId(operationDTO.getAccountId());
			operation.setAccount(account);
			return operation;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public OperationDTO fromOperation(Operation operation) {
		
		try {
			OperationDTO operationDTO = new OperationDTO();
			operationDTO.setId(operation.getId());
			operationDTO.setAmount(operation.getAmount());
			operationDTO.setType(operation.getType());
			operationDTO.setDate(operation.getDate());
			operationDTO.setDescription(operation.getDescription());
			
			operationDTO.setAccountId(operation.getAccount().getId());
			return operationDTO;
		}catch(Exception e) {
			return null;
		}
		
	}

}
