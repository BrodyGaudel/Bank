package com.brody.ebank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.dto.ContactDTO;
import com.brody.ebank.dto.CustomerDTO;
import com.brody.ebank.entities.Account;
import com.brody.ebank.entities.Contact;
import com.brody.ebank.entities.Customer;
import com.brody.ebank.enums.Sexe;
import com.brody.ebank.enums.Status;
import com.brody.ebank.repositories.CustomerRepository;

@SpringBootTest
class CustomerServiceImplTest {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Test
	void testSave() {
		
		String cin = UUID.randomUUID().toString();
		ContactDTO contactDTO = new ContactDTO(null, cin, "brody@spring.io", "12345678", "World");
		AccountDTO accountDTO = new AccountDTO(null, "rib", Status.CREATED, new BigDecimal(500));
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(null);
		customerDTO.setDateOfBirth(new Date());
		customerDTO.setPlaceOfBirth("GABON");
		customerDTO.setFirstname("Brody Gaudel");
		customerDTO.setName("MOUNANGA");
		customerDTO.setNationality("GABON");
		customerDTO.setSexe(Sexe.Homme);
		customerDTO.setAccountDTO(accountDTO);
		customerDTO.setContactDTO(contactDTO);
		
		CustomerDTO savedCustomerDTO = new CustomerDTO();
		try {
			savedCustomerDTO = customerServiceImpl.save(customerDTO);
		}catch(Exception e) {
			savedCustomerDTO=null;
		}
		assertNotNull(savedCustomerDTO);
		
		
	}

	@Test
	void testUpdate() {
		Contact contact = new Contact();
		contact.setAddress("WORLD");
		contact.setCin(UUID.randomUUID().toString());
		contact.setId(null);
		Account account = new Account();
		account.setId(null);
		account.setBalance(new BigDecimal(100));
		account.setStatus(Status.ACTIVATED);
		
		Customer customer = new Customer();
		customer.setId(null);
		customer.setDateOfBirth(new Date());
		customer.setPlaceOfBirth("GABON");
		customer.setFirstname("Brody Gaudel");
		customer.setName("MOUNANGA");
		customer.setNationality("GABON");
		customer.setSexe(Sexe.Homme);
		customer.setAccount(account);
		customer.setContact(contact);
		Customer customerSaved = customerRepository.save(customer);
		
		
		
		String cin = customerSaved.getContact().getCin();
		ContactDTO contactDTO = new ContactDTO(customerSaved.getContact().getId(), cin, "brody@spring.io", "12345678", "World");
		AccountDTO accountDTO = new AccountDTO(customerSaved.getAccount().getId(), "rib", Status.ACTIVATED, new BigDecimal(500));
		
		
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId((long)1);
		customerDTO.setDateOfBirth(new Date());
		customerDTO.setPlaceOfBirth("GABON");
		customerDTO.setFirstname("Brody Gaudel");
		customerDTO.setName("MOUNANGA");
		customerDTO.setNationality("GABON");
		customerDTO.setSexe(Sexe.Homme);
		customerDTO.setAccountDTO(accountDTO);
		customerDTO.setContactDTO(contactDTO);
		
		CustomerDTO updatedCustomerDTO = new CustomerDTO();
		try {
			updatedCustomerDTO = customerServiceImpl.update(customerSaved.getId(),customerDTO);
		}catch(Exception e) {
			updatedCustomerDTO=null;
		}
		assertNotNull(updatedCustomerDTO);
		assertEquals(updatedCustomerDTO.getFirstname(), customerDTO.getFirstname());
		
	}

	@Test
	void testFindById() {
		Contact contact = new Contact();
		contact.setAddress("WORLD");
		contact.setCin(UUID.randomUUID().toString());
		contact.setId(null);
		Account account = new Account();
		account.setId(null);
		account.setBalance(new BigDecimal(100));
		account.setStatus(Status.ACTIVATED);
		
		Customer customer = new Customer();
		customer.setId(null);
		customer.setDateOfBirth(new Date());
		customer.setPlaceOfBirth("GABON");
		customer.setFirstname("Brody Gaudel");
		customer.setName("MOUNANGA");
		customer.setNationality("GABON");
		customer.setSexe(Sexe.Homme);
		customer.setAccount(account);
		customer.setContact(contact);
		Customer customerSaved = customerRepository.save(customer);
		
		CustomerDTO customerDTO = new CustomerDTO();
		Long id = customerSaved.getId();
		try {
			customerDTO = customerServiceImpl.findById(id);
		}catch(Exception e) {
			customerDTO = null;
		}
		assertNotNull(customerDTO);
		assertEquals(customerDTO.getId(), id);
	}

	@Test
	void testFindByCin() {
		Contact contact = new Contact();
		contact.setAddress("WORLD");
		contact.setCin(UUID.randomUUID().toString());
		contact.setId((long)1);
		Account account = new Account();
		account.setId((long)1);
		account.setBalance(new BigDecimal(100));
		account.setStatus(Status.ACTIVATED);
		
		Customer customer = new Customer();
		customer.setId((long)1);
		customer.setDateOfBirth(new Date());
		customer.setPlaceOfBirth("GABON");
		customer.setFirstname("Brody Gaudel");
		customer.setName("MOUNANGA");
		customer.setNationality("GABON");
		customer.setSexe(Sexe.Homme);
		customer.setAccount(account);
		customer.setContact(contact);
		Customer customerSaved = customerRepository.save(customer);
		
		
		CustomerDTO customerDTO = customerServiceImpl.findByCin(customerSaved.getContact().getCin());
		assertNotNull(customerDTO);
		assertEquals(customerDTO.getContactDTO().getCin(), customerSaved.getContact().getCin());
	}

	@Test
	void testFindAll() {
		List<CustomerDTO> customerDTOS = customerServiceImpl.findAll();
		List<Customer> customers = customerRepository.findAll();
		assertEquals(customerDTOS.size(), customers.size());
		assertNotNull(customerDTOS);
		assertEquals(customerDTOS.get(0).getId(), customers.get(0).getId());
	}

	@Test
	void testDeleteById() {
		
		Contact contact = new Contact();
		contact.setAddress("WORLD");
		contact.setCin("CIN");
		contact.setId((long)1);
		Account account = new Account();
		account.setId((long)1);
		account.setBalance(new BigDecimal(100));
		account.setStatus(Status.ACTIVATED);
		
		Customer customer = new Customer();
		customer.setId((long)1);
		customer.setDateOfBirth(new Date());
		customer.setPlaceOfBirth("GABON");
		customer.setFirstname("Brody Gaudel");
		customer.setName("MOUNANGA");
		customer.setNationality("GABON");
		customer.setSexe(Sexe.Homme);
		customer.setAccount(account);
		customer.setContact(contact);
		Customer customerSaved = customerRepository.save(customer);
		
		customerServiceImpl.deleteById(customerSaved.getId());
		CustomerDTO customerDTO = new CustomerDTO();
		try {
			customerDTO = customerServiceImpl.findById(customerSaved.getId());
		}catch(Exception e) {
			customerDTO = null;
		}
		assertNull(customerDTO);
	}
	
	

}
