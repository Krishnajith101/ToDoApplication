package com.event.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eId;

	private String name;
	private String description;
	private LocalDateTime dateTime;
	private String status="Pending";

	public Event() {
	}
	
	public Event(int eId, String name, String description, LocalDateTime dateTime, String status) {
		super();
		this.eId = eId;
		this.name = name;
		this.description = description;
		this.dateTime = dateTime;
		this.status = status;
	}

	public Event(String name, String description, LocalDateTime dateTime) {
		super();
		this.name = name;
		this.description = description;
		this.dateTime = dateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}
	
	
}
