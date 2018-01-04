package com.crexos.model.dao;

import java.util.List;

import com.crexos.model.beans.Book;

/*
 *	Une interface DAO qui hérite de InterfaceDAO pour gérer les opérations
 *	de base de données requise pour manipuler une entité Book
 *	Cette classe commporte des méthodes propre à l'entité
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
	 * Obtenir tout les livres, cette méthodes est utile pour faire de la pagination
	 * @param offset Index de début de récupération des résultat (voir oc SQL LIMIT pour plus de détail)
	 * @param noOfRecords Nombre de résultat que l'on veut récupérer
	 * @return Liste de livres
	 */
	List<Book> getAll(int offset, int noOfRecords);
	
	/*
	 * Obtenir tout les livres avec une tri sur une colonne spécifique et un ordre particulier
	 * @param  column la colonne que l'on souahite trier
	 * @param mode le mode e tri, ASC ou DESC
	 * @return Liste de livres
	 */
	List<Book> getAllSortedBy(String column, String mode);
	
	/*
	 * Obtenir toute la liste de livre, trié avec option de pagination
	 * @param  column la colonne que l'on souahite trier
	 * @param mode le mode e tri, ASC ou DESC
	 * @param offset Index de début de récupération des résultat (voir oc SQL LIMIT pour plus de détail)
	 * @param noOfRecords Nombre de résultat que l'on veut récupérer
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
	 * Supprime la jointure entre un auteur et un livre, utiliser cette méthode sur tout les auteurs
	 * du livre avant de supprimer le livre
	 * @param authorId L'identifiant de l'auteur appartenant au livre
	 * @param bookId L'identifiant du livre
	 * @return Un boolean confirmant que le livre est bien supprimé
	 */
	boolean deleteJoinAuthorBook(int authorId, int bookId);
	
	/*
	 * Ajoute une jointure entre un livre et un auteur
	 * @param bookId l'identifiant du livre
	 * @param authorId l'identifiant de l'auteur
	 * @return Un boolean confirmant que la méthoe s'est bien déroulé
	 */
	boolean joinAuthor(int bookId, int authorId);
	
	/*
	 * Vérifie si une jointure existe, cette méthode permet d'éviter d'avoir le même auteur pour un même livre
	 * @param bookId L'identifiant du livre
	 * @param authorId L'identifiant de l'auteur
	 * @return Un boolean qui confirme si un auteur appartien au livre
	 */
	boolean existJoin(int bookId, int authorId);
	
	/*
	 * Nombre de livre en base de donnée, cette méthode sert pour faire la pagination
	 * @return l'entier correspondant au nombre total de livre en base de donnée
	 */
	int getSize();
}
