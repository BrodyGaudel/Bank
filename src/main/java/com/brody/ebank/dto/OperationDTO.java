package com.brody.ebank.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.brody.ebank.enums.Type;

public class OperationDTO {
	
	private Long id;
	private Date date;
	private BigDecimal amount;
	private Type type;
	private String description;
	private Long accountId;
	
	public OperationDTO(Long id, Date date, BigDecimal amount, Type type, String description, Long accountId) {
	
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.accountId = accountId;
	}

	public OperationDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "OperationDTO [id=" + id + ", date=" + date + ", amount=" + amount + ", type=" + type + ", description="
				+ description + ", accountId=" + accountId + "]";
	}
	
	
	

}
