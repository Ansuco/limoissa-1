package com.crexos.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.crexos.model.utils.Country;

@Entity
public class Author
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, columnDefinition = "INT(10) UNSIGNED")
	public int id;
	@Column(length=70, nullable=false)
	public String firstName;
	@Column(length=70, nullable=false)
	public String lastName;
	@Column(name="native_country", nullable=true)
	@Enumerated(EnumType.STRING)
	public Country nativeCountry;
	
	@ManyToMany(cascade=CascadeType.PERSIST, mappedBy="authors")
	private List<Book> books = new ArrayList<Book>();
	
	public Author(){}
	
	public Author(String fisrtname, String lastname, Country nativeCountry)
	{
		this.setFirstname(fisrtname);
		this.setLastName(lastname);
		this.setNativeCountry(nativeCountry);
	}
	
	public Author(int id, String fisrtname, String lastname, Country nativeCountry)
	{
		this(fisrtname, lastname, nativeCountry);
		this.setId(id);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstname(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lasttName)
	{
		this.lastName = lasttName;
	}

	public Country getNativeCountry()
	{
		return nativeCountry;
	}

	public void setNativeCountry(Country nativeCountry)
	{
		this.nativeCountry = nativeCountry;
	}
	
	public void setNativeCountry(String nativeCountry)
	{
		this.nativeCountry = Country.valueOf(nativeCountry);
	}
}
