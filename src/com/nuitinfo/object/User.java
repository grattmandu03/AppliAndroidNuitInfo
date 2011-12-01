package com.nuitinfo.object;

import java.util.Date;

public class User {
	
	private int id;
	private String email;
	private String password;
	private String lastname;
	private String firstname;
	private Date birthDate;
	
	public User(int id, String email, String password, String lastname, String firstname, Date birthdate){
		this.id = id;
		this.email = email;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthDate = birthdate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
