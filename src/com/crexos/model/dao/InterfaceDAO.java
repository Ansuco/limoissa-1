package com.crexos.model.dao;

import java.util.List;

/*
 * Une interface DAO g�n�rique pour g�rer l'op�ration de base de donn�es 
 * requise pour manipuler la persistence des entit�s de la librairie
 */
public interface InterfaceDAO<T>
{
	/*
	 * Obtenir une Entit�
	 * @param id L'identifiant de l'entit�
	 * @return L'entit� sp�cifi�e par son identifiant
	 */
	public T getById(int id);
	
	/*
	 * Supprimer une Entit�
	 * @param id L'identifiant de l'entit� � supprimer
	 */
	public void delete(int id);
	
	/*
	 * Mettre � jour une entit�
	 * @param obj Une entit� comportant les modifications � apporter identifi� par son id en 
	 * 			  base de donn�e
	 * @see com.crexos.model.dao.InterfaceDAO#update(java.lang.Object)
	 */
	public void update(T obj);
	
	/*
	 * Cr�� une entit�
	 * @param obj Une entit� que l'on veut ajouter en base de donn�e
	 * @return L'ientifiant de l'entit� cr�� ou celui de l'entit� qui
	 * 		   existe en base de donn�e  et qui a les m�mes nom et pr�nom e obj
	 * @see com.crexos.model.dao.InterfaceDAO#create(java.lang.Object)
	 */
	public int create(T obj);
	
	/*
	 * Obtient la liste de tout les entit�s de la base de donn�e
	 * @return La liste des entit�s
	 */
	public List<T> getAll();
	
	/*
	 * V�rifie si une entit� existe en base de donn�e, chaque entit� sp�cifie ces crit�re de v�rifications
	 * @param obj L'entit� que l'on veut v�rifier l'existance en base de donn�e
	 * @return l'identifiant de l'entit� trouv� ou 0
	 */
	public int exist(T obj);
}
