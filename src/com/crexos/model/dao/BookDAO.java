package com.crexos.model.dao;

import java.util.List;

import com.crexos.model.beans.Book;

public interface BookDAO extends InterfaceDAO<Book>
{
	Book getById(int id);
	List<Book> getAll();
	int create(Book book);
	void update(Book book);
	void delete(int id);
}
