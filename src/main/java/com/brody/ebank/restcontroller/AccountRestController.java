package com.brody.ebank.restcontroller;

/**
 * @author brody gaudel
 * Account Rest API
 * link to save bank account POST:  http://localhost:8888/api/v1/account/save
 * link to update bank account PUT: http://localhost:8888/api/v1/account/update/{id}
 * link to get bank account by id GET : http://localhost:8888/api/v1/account/findById/{id}
 * link to get bank account by rib (bank details) GET: http://localhost:8888/api/v1/account/findByRib/{rib}
 * link to get all bank account GET : http://localhost:8888/api/v1/account/findAll
 * link to modify bank account status PUT : http://localhost:8888/api/v1/account/status
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

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.dto.StatusDTO;
import com.brody.ebank.exception.AccountNotFoundException;
import com.brody.ebank.service.AccountService;

@RestController
@RequestMapping("api/v1/account")
@CrossOrigin(origins = "*")
public class AccountRestController {
	
	private AccountService accountService;

	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public AccountDTO save(@RequestBody AccountDTO accountDTO) {
		return accountService.save(accountDTO);
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public AccountDTO update(@PathVariable(name="id") Long id, @RequestBody AccountDTO accountDTO) throws AccountNotFoundException{
		return accountService.update(id, accountDTO);
	}
	
	@GetMapping("/findById/{id}")
	@ResponseBody
	public AccountDTO findById(@PathVariable(name="id") Long id) throws AccountNotFoundException{
		return accountService.findById(id);
	}
	
	@GetMapping("/findByRib/{rib}")
	@ResponseBody
	public AccountDTO findByRib(@PathVariable(name="rib") String rib){
		return accountService.findByRib(rib);
		
	}
	
	@GetMapping("/findAll")
	@ResponseBody
	public List<AccountDTO> findAll(){
		return accountService.findAll();
	}
	
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable(name="id") Long id){
		accountService.deleteById(id);
	}
	
	/**
	 * status =1 SUSPENDED
	 * status = 0 ACTIVATED
	 * status = -1 CREATED
	 * @param statusDTO
	 * @return
	 * @throws AccountNotFoundException
	 */
	@PutMapping("/status")
	@ResponseBody
	AccountDTO updateStatus(@RequestBody StatusDTO statusDTO) throws AccountNotFoundException{
		return accountService.updateStatus(statusDTO.getId(), statusDTO.getStatus());
	}

}
