package com.crexos.model.dao;

import java.util.List;

import com.crexos.model.beans.Book;

/*
 *	Une interface DAO qui h�rite de InterfaceDAO pour g�rer les op�rations
 *	de base de donn�es requise pour manipuler une entit� Book
 *	Cette classe commporte des m�thodes propre � l'entit�
 */
public interface BookDAO extends InterfaceDAO<Book>
{
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#getById(int)
	 */
	Book getById(int id);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#getAll()
	 */
	List<Book> getAll();
	
	/*
	 * Obtenir tout les livres, cette m�thodes est utile pour faire de la pagination
	 * @param offset Index de d�but de r�cup�ration des r�sultat (voir oc SQL LIMIT pour plus de d�tail)
	 * @param noOfRecords Nombre de r�sultat que l'on veut r�cup�rer
	 * @return Liste de livres
	 */
	List<Book> getAll(int offset, int noOfRecords);
	
	/*
	 * Obtenir tout les livres avec une tri sur une colonne sp�cifique et un ordre particulier
	 * @param  column la colonne que l'on souahite trier
	 * @param mode le mode e tri, ASC ou DESC
	 * @return Liste de livres
	 */
	List<Book> getAllSortedBy(String column, String mode);
	
	/*
	 * Obtenir toute la liste de livre, tri� avec option de pagination
	 * @param  column la colonne que l'on souahite trier
	 * @param mode le mode e tri, ASC ou DESC
	 * @param offset Index de d�but de r�cup�ration des r�sultat (voir oc SQL LIMIT pour plus de d�tail)
	 * @param noOfRecords Nombre de r�sultat que l'on veut r�cup�rer
	 * @return Liste de livres  
	 */
	List<Book> getAllSortedBy(String column, String mode, int offset, int noOfRecords);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#create(java.lang.Object)
	 */
	int create(Book book);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#update(java.lang.Object)
	 */
	void update(Book book);
	
	/*
	 * @see com.crexos.model.dao.InterfaceDAO#delete(int)
	 */
	void delete(int id);
	
	/*
	 * Supprime la jointure entre un auteur et un livre, utiliser cette m�thode sur tout les auteurs
	 * du livre avant de supprimer le livre
	 * @param authorId L'identifiant de l'auteur appartenant au livre
	 * @param bookId L'identifiant du livre
	 * @return Un boolean confirmant que le livre est bien supprim�
	 */
	boolean deleteJoinAuthorBook(int authorId, int bookId);
	
	/*
	 * Ajoute une jointure entre un livre et un auteur
	 * @param bookId l'identifiant du livre
	 * @param authorId l'identifiant de l'auteur
	 * @return Un boolean confirmant que la m�thoe s'est bien d�roul�
	 */
	boolean joinAuthor(int bookId, int authorId);
	
	/*
	 * V�rifie si une jointure existe, cette m�thode permet d'�viter d'avoir le m�me auteur pour un m�me livre
	 * @param bookId L'identifiant du livre
	 * @param authorId L'identifiant de l'auteur
	 * @return Un boolean qui confirme si un auteur appartien au livre
	 */
	boolean existJoin(int bookId, int authorId);
	
	/*
	 * Nombre de livre en base de donn�e, cette m�thode sert pour faire la pagination
	 * @return l'entier correspondant au nombre total de livre en base de donn�e
	 */
	int getSize();
}
