/**
 * @author brody gaudel
 * OPERATION REST API
 */


package com.brody.ebank.restcontroller;

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
@RequestMapping("api/v2/operation")
@CrossOrigin(origins = "*")
public class OperationRestController {
	
	private OperationService operationService;

	public OperationRestController(OperationService operationService) {
		this.operationService = operationService;
	}

	/**
	 * debit an account
	 * POST: http://localhost:8888/api/v2/operation/debit
	 * @param debitDTO DebitDTO
	 * @return DebitDTO
	 * @throws AccountNotFoundException Account Not Found Exception
	 * @throws BalanceNotSufficientException Balance Not Sufficient Exception
	 * @throws AccountSuspendedException Account Suspended Exception
	 * @throws AccountNotActivatedException Account Not Activated Exception
	 */
	@PostMapping("/debit")
	@ResponseBody
	public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException{
		operationService.debit(debitDTO);
		return debitDTO;
	}

	/**
	 * credit an account
	 * POST: http://localhost:8888/api/v2/operation/credit
	 * @param creditDTO CreditDTO
	 * @return CreditDTO
	 * @throws AccountNotFoundException Account Not Found Exception
	 * @throws BalanceNotSufficientException Balance Not Sufficient Exception
	 * @throws AccountSuspendedException Account Suspended Exception
	 * @throws AccountNotActivatedException Account Not Activated Exception
	 */
	@PostMapping("/credit")
	@ResponseBody
	public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException{
		operationService.credit(creditDTO);
		return creditDTO;
	}

	/**
	 * transfer money from an account to another account
	 * POST: http://localhost:8888/api/v2/operation/transfer
	 * @param transferDTO TransferDTO
	 * @return TransferDTO
	 * @throws AccountNotFoundException Account Not Found Exception
	 * @throws BalanceNotSufficientException Balance Not Sufficient Exception
	 * @throws AccountSuspendedException Account Suspended Exception
	 * @throws AccountNotActivatedException Account Not Activated Exception
	 */
	@PostMapping("/transfer")
	@ResponseBody
	public TransferDTO transfer(@RequestBody TransferDTO transferDTO) throws AccountNotFoundException, BalanceNotSufficientException, AccountSuspendedException, AccountNotActivatedException{
		operationService.transfer(transferDTO);
		return transferDTO;
	}

	/**
	 * get list of operations of an account by its account id
	 * GET: http://localhost:8888/api/v2/operation/findAllByAccountId/{accountId}
	 * @param accountId type Long
	 * @return List<OperationDTO>
	 */
	@GetMapping("/findAllByAccountId/{accountId}")
	@ResponseBody
	public List<OperationDTO> findAllByAccountId(@PathVariable(name="accountId") Long accountId){
		return operationService.findAllByAccountId(accountId);
	}

	/**
	 * find history of a bank account by account id
	 * GET: http://localhost:8888/api/v1/operation/{accountId}/pageOperations?page=0&size=5
	 * @param accountId type Long
	 * @param page type int
	 * @param size type int
	 * @return HistoryDTO
	 * @throws AccountNotFoundException Account Not Found Exception
	 */
	@GetMapping("/{accountId}/pageOperations")
	@ResponseBody
	public HistoryDTO findHistory(@PathVariable Long accountId,
			@RequestParam(name ="page", defaultValue = "0") int page,
			@RequestParam(name ="size", defaultValue = "5") int size) throws AccountNotFoundException{
		return operationService.findHistory(accountId, page, size);
	}
	
	

}
