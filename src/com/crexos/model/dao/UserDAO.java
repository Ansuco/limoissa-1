package com.crexos.model.dao;

import com.crexos.model.beans.User;

/*
 *	Une interface DAO qui hérite de InterfaceDAO pour gérer les opérations
 *	de base de données requise pour manipuler une entité User
 *	Cette classe commporte des méthodes propre à l'entité
 */
public interface UserDAO extends InterfaceDAO<User>
{
	/*
	 * Obtenir l'entité par son pseudo et mot de passe
	 * @param pseudo Pseudo de l'utilisateur existant en base de donnée
	 * @param password Mot de passe en clair de l'utilisateur
	 */
	public User getByPseudoPass(String pseudo, String password);
}
