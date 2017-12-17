package com.crexos.model.dao;

import java.util.List;

import com.crexos.model.beans.Author;

public interface AuthorDAO extends InterfaceDAO<Author>
{
	public Author getById(int id);
	public void delete(int id);
	public void update(Author obj);
	public int create(Author obj);
	public List<Author> getAll();
}
