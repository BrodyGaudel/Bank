package com.brody.ebank.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="compter")
public class Compter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;

	public Compter(Long id) {
		this.id = id;
	}

	public Compter() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Compter [id=" + id + "]";
	}

}
