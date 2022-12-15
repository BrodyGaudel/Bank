package com.brody.ebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.brody.ebank.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rib;
	private Status status;
	private BigDecimal balance;
	
	@Transient
	@OneToOne(mappedBy = "account")
	private Customer customer;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<Operation> operations;

	public Account(String rib, Status status, BigDecimal balance, Customer customer, List<Operation> operations) {
		this.rib = rib;
		this.status = status;
		this.balance = balance;
		this.customer = customer;
		this.operations = operations;
	}

	public Account() {
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", rib=" + rib + ", status=" + status + ", balance=" + balance + ", customer="
				+ customer + ", operations=" + operations + "]";
	}
	
	
}
