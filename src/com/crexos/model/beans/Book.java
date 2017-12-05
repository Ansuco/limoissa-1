package com.crexos.model.beans;

import java.util.HashSet;
import java.util.Set;

public class Book
{
	public int id;
	private String title;
	private String overview;
	private boolean availability;
	private float price;
	private Set<Author> authors;
	
	public Book()
	{
		this.setAuthors(new HashSet<Author>());
	}
	
	public Book(int id, String title, String overview, boolean availability, float price, Set<Author> authors)
	{
		this(title, overview, availability, price, authors);
		this.setId(id);
	}
	
	public Book(String title, String overview, boolean availability, float price, Set<Author> authors)
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

	public Set<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors(Set<Author> authors)
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
