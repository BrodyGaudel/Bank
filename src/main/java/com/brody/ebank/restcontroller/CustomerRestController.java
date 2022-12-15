package com.brody.ebank.restcontroller;

/**
 * @author brody gaudel
 * CUSTOMER REST API
 * link to save customer POST: http://localhost:8888/api/v1/customer/save
 * link to update customer PUT: http://localhost:8888/api/v1/customer/update/{id}
 * link to find customer by id GET: http://localhost:8888/api/v1/customer/findById/{id}
 * link to find customer by cin (passport id) GET: http://localhost:8888/api/v1/customer/findByCin/{cin}
 * link to find All customer GET: http://localhost:8888/api/v1/customer/findByAll
 * link to find customer by account id GET: http://localhost:8888/api/v1/customer/findByAccountId/{accountId}
 * 
 */

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brody.ebank.dto.CustomerDTO;
import com.brody.ebank.exception.CustomerExistException;
import com.brody.ebank.exception.CustomerNotFoundException;
import com.brody.ebank.service.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerRestController {
	
	private CustomerService customerService;

	public CustomerRestController(CustomerService customerService) {

		this.customerService = customerService;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public CustomerDTO save(@RequestBody  CustomerDTO customerDTO) throws CustomerExistException{
		return customerService.save(customerDTO);
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public CustomerDTO update(@PathVariable(name="id") Long id, @RequestBody  CustomerDTO customerDTO) throws CustomerNotFoundException, CustomerExistException{
		return customerService.update(id, customerDTO);
	}
	
	@GetMapping("/findById/{id}")
	@ResponseBody
	public CustomerDTO findById(@PathVariable(name="id") Long id) throws CustomerNotFoundException{
		return customerService.findById(id);
	}
	
	@GetMapping("/findByCin/{cin}")
	@ResponseBody
	public CustomerDTO findByCin(@PathVariable(name="cin") String cin){
		return customerService.findByCin(cin);
	}
	
	@GetMapping("/findAll")
	@ResponseBody
	public List<CustomerDTO> findAll(){
		return customerService.findAll();
	}
	
	@GetMapping("/findByAccountId/{accountId}")
	@ResponseBody
	public CustomerDTO findByAccountId(@PathVariable(name="accountId") Long accountId) {
		return customerService.findByAccountId(accountId);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable(name="id") Long id){
		customerService.deleteById(id);
	}

}
