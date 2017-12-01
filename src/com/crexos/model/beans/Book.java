package com.crexos.model.beans;

import java.util.HashSet;
import java.util.Set;

public class Book
{
	private int id;
	private String title;
	private String overview;
	private float price;
	private Set<Author> authors;
	
	public Book()
	{
		this.setAuthors(new HashSet<Author>());
	}
	
	public Book(int id, String title, String overview, float price, Set<Author> authors)
	{
		this(title, overview, price, authors);
		this.setId(id);
	}
	
	public Book(String title, String overview, float price, Set<Author> authors)
	{
		this();
		this.setTitle(title);
		this.setOverview(overview);
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
}
