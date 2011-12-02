package com.nuitinfo.object;

import java.util.Date;

public class Event {

	private int id;
	private int id_creator;
	private String victimName;
	private String description;
	private Date dueDate;
	private Boolean status;
	
	public Event(int id, int id_creator, String victimName, String description, Date dueDate, Boolean status){
		this.id = id;
		this.id_creator = id_creator;
		this.victimName = victimName;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_creator() {
		return id_creator;
	}
	public void setId_creator(int id_creator) {
		this.id_creator = id_creator;
	}
	public String getVictimName() {
		return victimName;
	}
	public void setVictimName(String victimName) {
		this.victimName = victimName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
