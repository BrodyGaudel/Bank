package com.brody.ebank.service;

/**
 * @author brody gaudel 
 * This class contains the implementation of the various functionalities
 *  of the customer management service of a bank
 */

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brody.ebank.dto.CustomerDTO;
import com.brody.ebank.entities.Account;
import com.brody.ebank.entities.Contact;
import com.brody.ebank.entities.Customer;
import com.brody.ebank.enums.Status;
import com.brody.ebank.exception.CustomerExistException;
import com.brody.ebank.exception.CustomerNotFoundException;
import com.brody.ebank.mappers.Mappers;
import com.brody.ebank.repositories.ContactRepository;
import com.brody.ebank.repositories.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	private ContactRepository contactRepository;
	private Mappers mappers;
	private GenerateRibService generateRibService;
	
	
	public CustomerServiceImpl(CustomerRepository customerRepository,
			ContactRepository contactRepository, Mappers mappers,
			GenerateRibService generateRibService) {
		
		this.customerRepository = customerRepository;
		this.contactRepository = contactRepository;
		this.mappers = mappers;
		this.generateRibService = generateRibService;
	}

	@Override
	public CustomerDTO save(CustomerDTO customerDTO) throws CustomerExistException {
		
		String cin = customerDTO.getContactDTO().getCin();
		Customer c = getByCin(cin);
		if(c!=null) {
			throw new CustomerExistException(" A CUSTOMER WITH CIN :"+cin+" EXIST");
		}
		
		Customer customer = mappers.fromCustomerDTO(customerDTO);
		Account account = mappers.fromAccountDTO(customerDTO.getAccountDTO());
		account.setBalance(BigDecimal.valueOf(0.0));
		account.setRib(generateRibService.generate());
		account.setStatus(Status.CREATED);
		account.setCustomer(customer);
		customer.setAccount(account);
		Customer customerSaved = customerRepository.save(customer);

		return mappers.fromCustomer(customerSaved);
	}

	@Override
	public CustomerDTO update(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException, CustomerExistException {
		
		
		Customer customer = customerRepository.findById(id).orElseThrow(
				()-> new CustomerNotFoundException("CUSTOMER NOT FOUND"));
		
		String cin = customerDTO.getContactDTO().getCin();
		Customer c = getByCin(cin);
		if(c!=null && !cin.equals(customer.getContact().getCin())) {
			throw new CustomerExistException(" A CUSTOMER WITH CIN :"+cin+" EXIST");
		}
		
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setPlaceOfBirth(customerDTO.getPlaceOfBirth());
		customer.setFirstname(customerDTO.getFirstname());
		customer.setName(customerDTO.getName());
		customer.setNationality(customerDTO.getNationality());
		customer.setSexe(customerDTO.getSexe());
		
		Contact contact = customer.getContact();
		contact.setAddress(customerDTO.getContactDTO().getAddress());
		contact.setCin(customerDTO.getContactDTO().getCin());
		contact.setMail(customerDTO.getContactDTO().getMail());
		contact.setPhone(customerDTO.getContactDTO().getPhone());
		customer.setContact(contact);
		
		Customer customerUpdated = customerRepository.save(customer);
		return mappers.fromCustomer(customerUpdated);
	}

	@Override
	public CustomerDTO findById(Long id) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow( ()-> new CustomerNotFoundException("CUSTOMER NOT FOUND"));
		return mappers.fromCustomer(customer);
	}

	@Override
	public CustomerDTO findByCin(String cin) {
		
		Customer customer = getByCin(cin);
		if(customer==null) {
			return null;
		}else {
			return mappers.fromCustomer(customer);
		}
		
	}

	@Override
	public List<CustomerDTO> findAll() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream()
				.map(customer -> mappers.fromCustomer(customer))
				.toList();
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
		
	}
	
	private Customer getByCin(String cin) {
		Contact contact = contactRepository.findByCin(cin);
		if(contact==null) {
			return null;
		}
		else {
			return customerRepository.findByContactId(contact.getId());
		}
	}

	@Override
	public CustomerDTO findByAccountId(Long accountId) {
		Customer customer = customerRepository.findByAccountId(accountId);
		return mappers.fromCustomer(customer);
	}

}
