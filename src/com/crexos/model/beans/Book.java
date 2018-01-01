package com.crexos.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Book
{
	public int id;
	private String title;
	private String overview;
	private boolean availability;
	private float price;
	
	@XmlElementWrapper(name = "authors")
	@XmlElement(name = "author")
	private List<Author> authors;
	
	public Book()
	{
		this.setAuthors(new ArrayList<Author>());
	}
	
	public Book(int id, String title, String overview, boolean availability, float price, List<Author> authors)
	{
		this(title, overview, availability, price, authors);
		this.setId(id);
	}
	
	public Book(String title, String overview, boolean availability, float price, List<Author> authors)
	{
		this();
		this.setTitle(title);
		this.setOverview(overview);
		this.setAvailability(availability);
		this.setPrice(price);
		this.setAuthors(authors);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getOverview()
	{
		return overview;
	}

	public void setOverview(String overview)
	{
		this.overview = overview;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public List<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors(List<Author> authors)
	{
		this.authors = authors;
	}

	public void addAuthor(Author author)
	{
		this.getAuthors().add(author);
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
}
