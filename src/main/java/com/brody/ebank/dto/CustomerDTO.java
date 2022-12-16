/**
 * @author brody gaudel
 * CUSTOMER DTO JSON 
 * You can use it to add or update customer in postman
 * {
        "id": null,
        "firstname": "Brody Gaudel",
        "name": "Brody Gaudel",
        "nationality": "WORLD",
        "dateOfBirth": "2000-09-20T15:15:58.000+00:00",
        "placeOfBirth": "WORLD",
        "sexe": "Homme",
        "contactDTO": {
            "id": null,
            "cin": "123CIN12",
            "mail": "abc@spring.io",
            "phone": "12345678",
            "address": "WORLD"
        },
        "accountDTO": {
            "id": null,
            "rib": "123456789",
            "status": "ACTIVATED",
            "balance": 10101010101.01
        }
    }
 */

package com.brody.ebank.dto;

import java.util.Date;

import com.brody.ebank.enums.Sexe;

public class CustomerDTO {
	
	private Long id;
	private String firstname;
	private String name;
	private String nationality;
	private Date dateOfBirth;
	private String placeOfBirth;
	private Sexe sexe;
	private ContactDTO contactDTO;
	private AccountDTO accountDTO;
	
	public CustomerDTO(Long id, String firstname, String name, String nationality, Date dateOfBirth,
			String placeOfBirth, Sexe sexe) {
		this.id = id;
		this.firstname = firstname;
		this.name = name;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
		this.sexe = sexe;
	}

	public CustomerDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public ContactDTO getContactDTO() {
		return contactDTO;
	}

	public void setContactDTO(ContactDTO contactDTO) {
		this.contactDTO = contactDTO;
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", firstname=" + firstname + ", name=" + name + ", nationality=" + nationality
				+ ", dateOfBirth=" + dateOfBirth + ", placeOfBirth=" + placeOfBirth + ", sexe=" + sexe + ", contactDTO="
				+ contactDTO + ", accountDTO=" + accountDTO + "]";
	}
	

}
