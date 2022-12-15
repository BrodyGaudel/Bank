package com.brody.ebank.restcontroller;

/**
 * @author brody gaudel
 * OPERATION REST API
 * 
 * link to debite an account POST: http://localhost:8888/api/v1/operation/debit
 * link to credite an account POST: http://localhost:8888/api/v1/operation/credit
 * link to make a transfer to account to another account POST: http://localhost:8888/api/v1/operation/transfer
 * link to get operations (list) by accountId GET: http://localhost:8888/api/v1/operation/findAllByAccountId/{accountId}
 * link to get operations (pageable) by an account GET: http://localhost:8888/api/v1/operation/{accountId}/pageOperations?page=0&size=5
 */


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brody.ebank.dto.CreditDTO;
import com.brody.ebank.dto.DebitDTO;
import com.brody.ebank.dto.HistoryDTO;
import com.brody.ebank.dto.OperationDTO;
import com.brody.ebank.dto.TransferDTO;
import com.brody.ebank.exception.AccountNotActivatedException;
import com.brody.ebank.exception.AccountNotFoundException;
import com.brody.ebank.exception.AccountSuspendedException;
import com.brody.ebank.exception.BalanceNotSufficientException;
import com.brody.ebank.service.OperationService;

@RestController
@RequestMapping("api/v1/operation")
@CrossOrigin(origins = "*")
public class OperationRestController {
	
	private OperationService operationService;

	public OperationRestController(OperationService operationService) {
		this.operationService = operationService;
	}
	
	@PostMapping("/debit")
	@ResponseBody
	public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException{
		operationService.debit(debitDTO);
		return debitDTO;
	}
	
	@PostMapping("/credit")
	@ResponseBody
	public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException{
		operationService.credit(creditDTO);
		return creditDTO;
	}
	
	@PostMapping("/transfer")
	@ResponseBody
	public TransferDTO transfer(@RequestBody TransferDTO transferDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException{
		operationService.transfert(transferDTO);
		return transferDTO;
	}
	
	@GetMapping("/findAllByAccountId/{accountId}")
	@ResponseBody
	public List<OperationDTO> findAllByAccountId(@PathVariable(name="accountId") Long accountId){
		return operationService.findAllByAccountId(accountId);
	}
	
	@GetMapping("/{accountId}/pageOperations")
	@ResponseBody
	public HistoryDTO findHistory(@PathVariable Long accountId,
			@RequestParam(name ="page", defaultValue = "0") int page,
			@RequestParam(name ="size", defaultValue = "5") int size) throws AccountNotFoundException{
		return operationService.findHistory(accountId, page, size);
	}
	
	

}
