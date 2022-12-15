package com.brody.ebank.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cin;
	private String mail;
	private String phone;
	private String address;
	
	@Transient
	@OneToOne(mappedBy = "contact")
	private Customer customer;

	public Contact(String cin, String mail, String phone, String address, Customer customer) {
		this.cin = cin;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.customer = customer;
	}
	
	public Contact() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", cin=" + cin + ", mail=" + mail + ", phone=" + phone + ", address=" + address
				+ ", customer=" + customer + "]";
	}
	
	
	
	

}
