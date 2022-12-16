/**
 * @author brody gaudel
 * This class contains the mapping functionality
 * between entities and dtos
 */

package com.brody.ebank.mappers;


import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MappersImpl implements Mappers {

	/**
	 * map ContactDTO to Contact
	 * @param contactDTO ContactDTO
	 * @return Contact
	 */
	@Override
	public Contact fromContactDTO(ContactDTO contactDTO) {
		log.info("In fromContactDTO()");
		try {
			Contact contact = new Contact();
			contact.setAddress(contactDTO.getAddress());
			contact.setCin(contactDTO.getCin());
			contact.setId(contactDTO.getId());
			contact.setMail(contactDTO.getMail());
			contact.setPhone(contactDTO.getPhone());
			log.info("ContactDTO Mapped To Contact");
			return contact;
		}catch(Exception e) {
			log.error("ContactDTO Not Mapped To Contact :"+e);
			return null;
		}
		
	}

	/**
	 * map Contact to ContactDTO
	 * @param contact Contact
	 * @return ContactDTO
	 */
	@Override
	public ContactDTO fromContact(Contact contact) {
		log.info("In fromContact)");
		try {
			ContactDTO contactDTO = new ContactDTO();
			contactDTO.setAddress(contact.getAddress());
			contactDTO.setCin(contact.getCin());
			contactDTO.setId(contact.getId());
			contactDTO.setMail(contact.getMail());
			contactDTO.setPhone(contact.getPhone());
			log.info("Contact Mapped To ContactDTO");
			return contactDTO;
		}catch(Exception e) {
			log.error("Contact Not Mapped To ContactDTO :"+e);
			return null;
		}
		
		
	}

	/**
	 * map AccountDTO to Account
	 * @param accountDTO AccountDTO
	 * @return Account
	 */
	@Override
	public Account fromAccountDTO(AccountDTO accountDTO) {
		log.info("In fromAccountDTO()");
		try {
			Account account = new Account();
			account.setBalance(accountDTO.getBalance());
			account.setId(accountDTO.getId());
			account.setRib(accountDTO.getRib());
			account.setStatus(accountDTO.getStatus());
			log.info("AccountDTO Mapped To Account");
			return account;
		}catch(Exception e) {
			log.error("AccountDTO Not Mapped To Account :"+e);
			return null;
		}
		
	}

	/**
	 * map Account to AccountDTO
	 * @param account Account
	 * @return AccountDTO
	 */
	@Override
	public AccountDTO fromAccount(Account account) {
		log.info("In fromAccount()");
		try {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setBalance(account.getBalance());
			accountDTO.setId(account.getId());
			accountDTO.setRib(account.getRib());
			accountDTO.setStatus(account.getStatus());
			log.info("Account Mapped To AccountDTO");
			return accountDTO;
		}catch(Exception e) {
			log.error("Account Not Mapped To AccountDTO :"+e);
			return null;
		}
		
	}

	/**
	 * Map CustomerDTO to Customer
	 * @param customerDTO CustomerDTO
	 * @return Customer
	 */
	@Override
	public Customer fromCustomerDTO(CustomerDTO customerDTO) {
		log.info("In fromCustomerDTO()");
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
			log.info("CustomerDTO Mapped To Customer");
			return customer;
		}catch(Exception e) {
			log.error("CustomerDTO Not Mapped To Customer :"+e);
			return null;
		}
		
	}

	/**
	 * map Customer to CustomerDTO
	 * @param customer Customer
	 * @return CustomerDTO
	 */
	@Override
	public CustomerDTO fromCustomer(Customer customer) {
		log.info("In fromCustomer()");
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
			log.info("Customer Mapped To CustomerDTO");
			return customerDTO;
		}catch(Exception e) {
			log.info("Customer Not Mapped To CustomerDTO :"+e);
			return null;
		}
		
	}

	/**
	 * map OperationDTO to Operation
	 * @param operationDTO OperationDTO
	 * @return Operation
	 */
	@Override
	public Operation fromOperationDTO(OperationDTO operationDTO) {
		log.info("In fromOperationDTO()");
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
			log.info("OperationDTO Mapped To Operation");
			return operation;
		}catch(Exception e) {
			log.error("OperationDTO Not Mapped To Operation :"+e);
			return null;
		}
		
	}

	/**
	 * map Operation to OperationDTO
	 * @param operation Operation
	 * @return OperationDTO
	 */
	@Override
	public OperationDTO fromOperation(Operation operation) {
		log.info("In fromOperation()");
		try {
			OperationDTO operationDTO = new OperationDTO();
			operationDTO.setId(operation.getId());
			operationDTO.setAmount(operation.getAmount());
			operationDTO.setType(operation.getType());
			operationDTO.setDate(operation.getDate());
			operationDTO.setDescription(operation.getDescription());
			
			operationDTO.setAccountId(operation.getAccount().getId());
			log.info("Operation Mapped To OperationDTO");
			return operationDTO;
		}catch(Exception e) {
			log.error("Operation Mapped To OperationDTO :"+e);
			return null;
		}
		
	}

}
