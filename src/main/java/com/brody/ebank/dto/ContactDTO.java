package com.brody.ebank.dto;

public class ContactDTO {
	
	private Long id;
	private String cin;
	private String mail;
	private String phone;
	private String address;
	
	public ContactDTO(Long id, String cin, String mail, String phone, String address) {
		
		this.id = id;
		this.cin = cin;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
	}

	public ContactDTO() {
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

	@Override
	public String toString() {
		return "ContactDTO [id=" + id + ", cin=" + cin + ", mail=" + mail + ", phone=" + phone + ", address=" + address
				+ "]";
	}

}
