package com.crexos.model.utils;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.crexos.model.beans.Book;

@XmlRootElement(name = "books")
public class ImportXMLBooks
{
	@XmlElementWrapper(name = "books")
	@XmlElement(name = "book")
	List<Book> books;

	@XmlElement(name = "book")
	public void setBooks(List<Book> books)
	{
		this.books = books;
	}

	public List<Book> getBooks()
	{
		return this.books;
	}
}
