package com.crexos.model.dao;

import java.util.List;

/*
 * Une interface DAO générique pour gérer l'opération de base de données 
 * requise pour manipuler la persistence des entités de la librairie
 */
public interface InterfaceDAO<T>
{
	/*
	 * Obtenir une Entité
	 * @param id L'identifiant de l'entité
	 * @return L'entité spécifiée par son identifiant
	 */
	public T getById(int id);
	
	/*
	 * Supprimer une Entité
	 * @param id L'identifiant de l'entité à supprimer
	 */
	public void delete(int id);
	
	/*
	 * Mettre à jour une entité
	 * @param obj Une entité comportant les modifications à apporter identifié par son id en 
	 * 			  base de donnée
	 * @see com.crexos.model.dao.InterfaceDAO#update(java.lang.Object)
	 */
	public void update(T obj);
	
	/*
	 * Créé une entité
	 * @param obj Une entité que l'on veut ajouter en base de donnée
	 * @return L'ientifiant de l'entité créé ou celui de l'entité qui
	 * 		   existe en base de donnée  et qui a les mêmes nom et prénom e obj
	 * @see com.crexos.model.dao.InterfaceDAO#create(java.lang.Object)
	 */
	public int create(T obj);
	
	/*
	 * Obtient la liste de tout les entités de la base de donnée
	 * @return La liste des entités
	 */
	public List<T> getAll();
	
	/*
	 * Vérifie si une entité existe en base de donnée, chaque entité spécifie ces critère de vérifications
	 * @param obj L'entité que l'on veut vérifier l'existance en base de donnée
	 * @return l'identifiant de l'entité trouvé ou 0
	 */
	public int exist(T obj);
}
