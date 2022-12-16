/**
 * @author brody gaudel
 * CUSTOMER REST API
 */

package com.brody.ebank.restcontroller;



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
@RequestMapping("api/v2/customer")
@CrossOrigin(origins = "*")
public class CustomerRestController {
	
	private CustomerService customerService;

	public CustomerRestController(CustomerService customerService) {

		this.customerService = customerService;
	}

	/**
	 * create a new customer
	 * POST: http://localhost:8888/api/v2/customer/save
	 * @param customerDTO CustomerDTO
	 * @return CustomerDTO
	 * @throws CustomerExistException Customer Exist Exception
	 */
	@PostMapping("/save")
	@ResponseBody
	public CustomerDTO save(@RequestBody  CustomerDTO customerDTO) throws CustomerExistException{
		return customerService.save(customerDTO);
	}

	/**
	 * update a customer
	 * PUT: http://localhost:8888/api/v2/customer/update/{id}
	 * @param id type Long
	 * @param customerDTO CustomerDTO
	 * @return CustomerDTO
	 * @throws CustomerNotFoundException Customer Not Found Exception
	 * @throws CustomerExistException Customer Exist Exception
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public CustomerDTO update(@PathVariable(name="id") Long id, @RequestBody  CustomerDTO customerDTO) throws CustomerNotFoundException, CustomerExistException{
		return customerService.update(id, customerDTO);
	}

	/**
	 * get a customer by its id
	 * GET: http://localhost:8888/api/v2/customer/findById/{id}
	 * @param id type Long
	 * @return CustomerDTO
	 * @throws CustomerNotFoundException Customer Not Found Exception
	 */
	@GetMapping("/findById/{id}")
	@ResponseBody
	public CustomerDTO findById(@PathVariable(name="id") Long id) throws CustomerNotFoundException{
		return customerService.findById(id);
	}

	/**
	 * get a customer by its cin
	 * GET: http://localhost:8888/api/v2/customer/findByCin/{cin}
	 * @param cin type String
	 * @return CustomerDTO
	 */
	@GetMapping("/findByCin/{cin}")
	@ResponseBody
	public CustomerDTO findByCin(@PathVariable(name="cin") String cin){
		return customerService.findByCin(cin);
	}

	/**
	 * get list of customers
	 * GET: http://localhost:8888/api/v2/customer/findAll
	 * @return List<CustomerDTO>
	 */
	@GetMapping("/findAll")
	@ResponseBody
	public List<CustomerDTO> findAll(){
		return customerService.findAll();
	}

	/**
	 * get a customer by its bank account id
	 * GET: http://localhost:8888/api/v2/customer/findByAccountId/{accountId}
	 * @param accountId type Long
	 * @return CustomerDTO
	 */
	@GetMapping("/findByAccountId/{accountId}")
	@ResponseBody
	public CustomerDTO findByAccountId(@PathVariable(name="accountId") Long accountId) {
		return customerService.findByAccountId(accountId);
	}

	/**
	 * delete a customer by its if
	 * DELETE: http://localhost:8888/api/v2/customer/deleteById/{id}
	 * @param id type Long
	 */
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable(name="id") Long id){
		customerService.deleteById(id);
	}

}
