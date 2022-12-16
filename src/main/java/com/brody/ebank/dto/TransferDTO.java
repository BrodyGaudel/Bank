/**
 * @author brody gaudel
 * TRANSFER DTO JSON
 * You can use it to transfer money from an account to another account in postman
 * {
"accountSource":"20442044100000",
"accountDestination":"20442044100001",
"amount":356489,
"description": "Transfer of money"
}
 */


package com.brody.ebank.dto;


import java.math.BigDecimal;

public class TransferDTO {
	
	private String accountSource;
	private String accountDestination;
	private BigDecimal amount;
	private String description;
	
	public TransferDTO(String accountSource, String accountDestination, BigDecimal amount, String description) {
		this.accountSource = accountSource;
		this.accountDestination = accountDestination;
		this.amount = amount;
		this.description = description;
	}

	public TransferDTO() {
		super();
	}

	public String getAccountSource() {
		return accountSource;
	}

	public void setAccountSource(String accountSource) {
		this.accountSource = accountSource;
	}

	public String getAccountDestination() {
		return accountDestination;
	}

	public void setAccountDestination(String accountDestination) {
		this.accountDestination = accountDestination;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TransferDTO [accountSource=" + accountSource + ", accountDestination=" + accountDestination
				+ ", amount=" + amount + ", description=" + description + "]";
	}

}
