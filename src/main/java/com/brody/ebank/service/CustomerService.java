package com.brody.ebank.service;

import java.util.List;

import com.brody.ebank.dto.CustomerDTO;
import com.brody.ebank.exception.CustomerExistException;
import com.brody.ebank.exception.CustomerNotFoundException;

public interface CustomerService {
	
	CustomerDTO save(CustomerDTO customerDTO) throws CustomerExistException;
	CustomerDTO update(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException, CustomerExistException;
	CustomerDTO findById(Long id) throws CustomerNotFoundException;
	CustomerDTO findByCin(String cin);
	List<CustomerDTO> findAll();
	CustomerDTO findByAccountId(Long accountId);
	void deleteById(Long id);
}
