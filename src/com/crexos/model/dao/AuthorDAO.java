package com.crexos.model.dao;

import java.util.List;

import com.crexos.model.beans.Author;

/*
 *	Une interface DAO qui hérite de interface DAO pour gérer les opérations
 *	de base de données requise pour manipuler une entité Author
 *	Cette classe peut commporte des méthodes propre à l'entité
 */
public interface AuthorDAO extends InterfaceDAO<Author>
{
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#getById(int)
	 */
	public Author getById(int id);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#delete(int)
	 */
	public void delete(int id);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#update(java.lang.Object)
	 */
	public void update(Author obj);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#create(java.lang.Object)
	 */
	public int create(Author obj);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#getAll()
	 */
	public List<Author> getAll();
}
