package com.crexos.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, columnDefinition = "INT(10) UNSIGNED")
	private int id;
	@Column(length=70, nullable=false)
	private String pseudo;
	@Column(name="passwd", nullable=false, columnDefinition="char(40)")
	private String password;
	@Column(length=90, nullable=false)
	private String firstname;
	@Column(length=90, nullable=false)
	private String lastname;
	@Column(length=90, nullable=false)
	private String email;
	@Column(nullable=false, columnDefinition="varchar(20) default 'USER'")
	private String role;
	
	public User(){}
	
	public User(String firstname, String lastname, String pseudo, String email, String password)
	{
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
	}
	
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
