/**
 * @author brody gaudel
 * This class contains the implementation of the various functionalities
 *  of the customer management service of a bank
 */

package com.brody.ebank.service;



import java.math.BigDecimal;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

	/**
	 * Save a customer
	 * if the customer already exists: it throws an exception
	 * @param customerDTO request
	 * @return customerDTO saved response
	 * @throws CustomerExistException Customer with this CIN Already exist
	 */
	@Override
	public CustomerDTO save(CustomerDTO customerDTO) throws CustomerExistException {
		log.info("In save()");
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

		log.info("Account Saved");
		return mappers.fromCustomer(customerSaved);
	}

	/**
	 * update a customer
	 * @param id type Long
	 * @param customerDTO request
	 * @return CustomerDTO response
	 * @throws CustomerNotFoundException Customer Not Found
	 * @throws CustomerExistException Customer with CIN Already exist
	 */
	@Override
	public CustomerDTO update(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException, CustomerExistException {
		log.info("In update()");
		Customer customer = getCustomerById(id);
		
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
		log.info("Customer Updated");
		return mappers.fromCustomer(customerUpdated);
	}

	/**
	 * return customer by its id
	 * @param id type Long
	 * @return CustomerDTO found
	 * @throws CustomerNotFoundException Customer Not Found
	 */
	@Override
	public CustomerDTO findById(Long id) throws CustomerNotFoundException {
		log.info("In findById()");
		Customer customer = getCustomerById(id);
		log.info("Customer found");
		return mappers.fromCustomer(customer);
	}

	/**
	 * return customer by cin(passport id)
	 * @param cin type string
	 * @return CustomerDTO response
	 */
	@Override
	public CustomerDTO findByCin(String cin) {
		log.info("In foundByCin()");
		Customer customer = getByCin(cin);
		if(customer==null) {
			log.info("Customer not found return null");
			return null;
		}else {
			log.info("Customer found");
			return mappers.fromCustomer(customer);
		}
		
	}

	/**
	 * return list of customers
	 * @return List<CustomerDTO>
	 */
	@Override
	public List<CustomerDTO> findAll() {
		log.info("In findAll()");
		List<Customer> customers = customerRepository.findAll();
		log.info("Customer(s) found");
		return customers.stream()
				.map(customer -> mappers.fromCustomer(customer))
				.toList();
	}

	/**
	 * delete a customer by its id
	 * @param id type Long
	 */
	@Override
	public void deleteById(Long id) {
		log.info("In deleteById()");
		customerRepository.deleteById(id);
		log.info("Customer deleted");
	}

	/**
	 * return a customer by its cin or passport id
	 * @param cin type String
	 * @return Customer response
	 */
	private Customer getByCin(String cin) {
		log.info("In getByCin()");
		Contact contact = contactRepository.findByCin(cin);
		if(contact==null) {
			log.info("Customer Not Found Return Null");
			return null;
		}
		else {
			log.info("Customer Found");
			return customerRepository.findByContactId(contact.getId());
		}
	}

	/**
	 * return a customer by its bank account id
	 * @param accountId type Long
	 * @return CustomerDTO
	 */
	@Override
	public CustomerDTO findByAccountId(Long accountId) {
		log.info("In findByAccountId()");
		Customer customer = customerRepository.findByAccountId(accountId);
		log.info("Customer found ");
		return mappers.fromCustomer(customer);
	}

	/**
	 * return a customer by its id
	 * @param id type Long
	 * @return Customer response
	 * @throws CustomerNotFoundException Customer Not Found
	 */
	private Customer getCustomerById(Long id) throws CustomerNotFoundException{
		log.info("In getCustomerById()");
		return customerRepository.findById(id)
				.orElseThrow( ()-> new CustomerNotFoundException("CUSTOMER NOT FOUND"));
	}

}
