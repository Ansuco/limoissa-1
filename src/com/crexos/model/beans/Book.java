package com.crexos.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, columnDefinition = "INT(10) UNSIGNED")
	public int id;
	@Column(length=70)
	private String title;
	@Column(columnDefinition = "TINYINT(1) default 1")
	private boolean availability;
	@Column(precision=5, scale=2, columnDefinition = "DOUBLE(5,2) default 0.00")
	private float price;
	@Column(columnDefinition="text")
	private String overview;
	
	@ManyToMany
	@JoinTable(
		name="Authors_Books",
		joinColumns=@JoinColumn(name="book_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_books")),
		inverseJoinColumns=@JoinColumn(name="author_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_authors"))
	)
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
		author.addBook(this);
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
}
