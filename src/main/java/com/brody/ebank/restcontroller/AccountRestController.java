/**
 * @author brody gaudel
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

import com.brody.ebank.dto.AccountDTO;
import com.brody.ebank.dto.StatusDTO;
import com.brody.ebank.exception.AccountNotFoundException;
import com.brody.ebank.service.AccountService;

@RestController
@RequestMapping("api/v2/account")
@CrossOrigin(origins = "*")
public class AccountRestController {
	
	private AccountService accountService;

	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * create an account
	 * POST:  http://localhost:8888/api/v2/account/save
	 * @param accountDTO AccountDTO
	 * @return AccountDTO
	 */
	@PostMapping("/save")
	@ResponseBody
	public AccountDTO save(@RequestBody AccountDTO accountDTO) {
		return accountService.save(accountDTO);
	}

	/**
	 * Update an account
	 * PUT:  http://localhost:8888/api/v2/account/update/{id}
	 * @param id type Long
	 * @param accountDTO AccountDTO
	 * @return AccountDTO
	 * @throws AccountNotFoundException Account Not Found Exception
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	public AccountDTO update(@PathVariable(name="id") Long id, @RequestBody AccountDTO accountDTO) throws AccountNotFoundException{
		return accountService.update(id, accountDTO);
	}

	/**
	 * get account by its id
	 *  GET : http://localhost:8888/api/v2/account/findById/{id}
	 * @param id type Long
	 * @return AccountDTO
	 * @throws AccountNotFoundException Account Not Found Exception
	 */
	@GetMapping("/findById/{id}")
	@ResponseBody
	public AccountDTO findById(@PathVariable(name="id") Long id) throws AccountNotFoundException{
		return accountService.findById(id);
	}

	/**
	 * Get an account by its RIB
	 * GET : http://localhost:8888/api/v2/account/findByRib/{rib}
	 * @param rib type String
	 * @return AccountDTO
	 */
	@GetMapping("/findByRib/{rib}")
	@ResponseBody
	public AccountDTO findByRib(@PathVariable(name="rib") String rib){
		return accountService.findByRib(rib);
		
	}

	/**
	 * get list of accounts
	 * GET : http://localhost:8888/api/v2/account/findAll
	 * @return List<AccountDTO>
	 */
	@GetMapping("/findAll")
	@ResponseBody
	public List<AccountDTO> findAll(){
		return accountService.findAll();
	}

	/**
	 * delete account by id
	 * DELETE : http://localhost:8888/api/v2/account/deleteById/{id}
	 * @param id type Long
	 */
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable(name="id") Long id){
		accountService.deleteById(id);
	}
	
	/**
	 * GET : http://localhost:8888/api/v2/account/status
	 * status =1 SUSPENDED
	 * status = 0 ACTIVATED
	 * status = -1 CREATED
	 * @param statusDTO StatusDTO
	 * @return AccountDTO
	 * @throws AccountNotFoundException Account Not Found Exception
	 */
	@PutMapping("/status")
	@ResponseBody
	public AccountDTO updateStatus(@RequestBody StatusDTO statusDTO) throws AccountNotFoundException{
		return accountService.updateStatus(statusDTO.getId(), statusDTO.getStatus());
	}

}
