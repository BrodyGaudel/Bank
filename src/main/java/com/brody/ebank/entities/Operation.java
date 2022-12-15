package com.brody.ebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.brody.ebank.enums.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operation")
public class Operation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	private String description;
	@ManyToOne
	private Account account;
	
	public Operation(Date date, BigDecimal amount, Type type, String description, Account account) {
		this.date = date;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.account = account;
	}

	public Operation() {
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", date=" + date + ", amount=" + amount + ", type=" + type + ", description="
				+ description + ", account=" + account + "]";
	}
	
}
