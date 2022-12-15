package com.brody.ebank.mappers;

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.dto.ContactDTO;
import com.brody.ebank.dto.CustomerDTO;
import com.brody.ebank.dto.OperationDTO;
import com.brody.ebank.entities.Account;
import com.brody.ebank.entities.Contact;
import com.brody.ebank.entities.Customer;
import com.brody.ebank.entities.Operation;

public interface Mappers {
	
	Contact fromContactDTO(ContactDTO contactDTO);
	ContactDTO fromContact(Contact contact);
	
	Account fromAccountDTO(AccountDTO accountDTO);
	AccountDTO fromAccount(Account account);
	
	Customer fromCustomerDTO(CustomerDTO customerDTO);
	CustomerDTO fromCustomer(Customer customer);
	
	Operation fromOperationDTO(OperationDTO operationDTO);
	OperationDTO fromOperation(Operation operation);
	
	
	
}
