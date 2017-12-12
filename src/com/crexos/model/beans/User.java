package com.crexos.model.beans;

public class User
{
	private int id;
	private String firstname;
	private String lastname;
	private String pseudo;
	private String email;
	private String password;
	private String role;
	
	public User(){}
	
	public User(String firstname, String lastname, String pseudo, String email, String password, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
	
	public User(int id, String firstname, String lastname, String pseudo, String email, String password, String role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.role = role;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
