package com.crexos.model.dao;

import java.util.List;

import com.crexos.model.beans.Author;

/*
 *	Une interface DAO qui h�rite de interface DAO pour g�rer les op�rations
 *	de base de donn�es requise pour manipuler une entit� Author
 *	Cette classe peut commporte des m�thodes propre � l'entit�
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
