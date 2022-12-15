package com.brody.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brody.ebank.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	Contact findByCin(String cin);
}
