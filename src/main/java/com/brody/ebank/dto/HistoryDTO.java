package com.brody.ebank.dto;

import java.math.BigDecimal;
import java.util.List;

public class HistoryDTO {
	
	private Long accountId;
	private BigDecimal balance;
	private int currentPage;
	private int totalPages;
	private int pageSize;
	private List<OperationDTO> operationDTOS;
	
	public HistoryDTO(Long accountId, BigDecimal balance, int currentPage, int totalPages, int pageSize,
			List<OperationDTO> operationDTOS) {
	
		this.accountId = accountId;
		this.balance = balance;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
		this.operationDTOS = operationDTOS;
	}

	public HistoryDTO() {
		super();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<OperationDTO> getOperationDTOS() {
		return operationDTOS;
	}

	public void setOperationDTOS(List<OperationDTO> operationDTOS) {
		this.operationDTOS = operationDTOS;
	}

	@Override
	public String toString() {
		return "HistoryDTO [accountId=" + accountId + ", balance=" + balance + ", currentPage=" + currentPage
				+ ", totalPages=" + totalPages + ", pageSize=" + pageSize + ", operationDTOS=" + operationDTOS + "]";
	}

}
