/**
 * @author brody gaudel
 * CREDIT DTO JSON 
 * You can use to credit an account on postman
 * here accountId is the rib of account not id
 * {
    "accountId": "20442044100000",
    "amount":10000000,
    "description": "virement"
    
	}
 */

package com.brody.ebank.dto;


import java.math.BigDecimal;

public class CreditDTO {
	
	private String accountId;
	private BigDecimal amount;
	private String description;
	
	public CreditDTO(String accountId, BigDecimal amount, String description) {
		this.accountId = accountId;
		this.amount = amount;
		this.description = description;
	}

	public CreditDTO() {
		super();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
		return "CreditDTO [accountId=" + accountId + ", amount=" + amount + ", description=" + description + "]";
	}
	
}
