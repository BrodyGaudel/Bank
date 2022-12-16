/**
 * 
 * @author brody gaudel
 * STATUS DTO JSON
 * You can use it to change bank account status on postman
 * if status equals 1 : suspend
 * if status equals 0 : activated
 * if status equals -1 : created
 *{
    "id":2,
    "status":0
	}
 *
 */

package com.brody.ebank.dto;

public class StatusDTO {
	
	private Long id;
	private int status;
	
	public StatusDTO(Long id, int status) {
		this.id = id;
		this.status = status;
	}

	public StatusDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusDTO [id=" + id + ", status=" + status + "]";
	}

}
