package com.crexos.model.beans;

import com.crexos.model.utils.Country;

public class Author
{
	public int id;
	public String firstName;
	public String lastName;
	public Country nativeCountry;
	
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
