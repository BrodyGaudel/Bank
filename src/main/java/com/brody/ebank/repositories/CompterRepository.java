package com.brody.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brody.ebank.entities.Compter;


public interface CompterRepository extends JpaRepository<Compter, Long> {

}
