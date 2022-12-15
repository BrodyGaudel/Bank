package com.brody.ebank.dto;

import java.math.BigDecimal;

import com.brody.ebank.enums.Status;

public class AccountDTO {
	
	private Long id;
	private String rib;
	private Status status;
	private BigDecimal balance;
	
	public AccountDTO(Long id, String rib, Status status, BigDecimal balance) {
		this.id = id;
		this.rib = rib;
		this.status = status;
		this.balance = balance;
	}

	public AccountDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", rib=" + rib + ", status=" + status + ", balance=" + balance + "]";
	}
	
}
