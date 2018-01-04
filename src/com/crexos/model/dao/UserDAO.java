package com.crexos.model.dao;

import com.crexos.model.beans.User;

/*
 *	Une interface DAO qui h�rite de InterfaceDAO pour g�rer les op�rations
 *	de base de donn�es requise pour manipuler une entit� User
 *	Cette classe commporte des m�thodes propre � l'entit�
 */
public interface UserDAO extends InterfaceDAO<User>
{
	/*
	 * Obtenir l'entit� par son pseudo et mot de passe
	 * @param pseudo Pseudo de l'utilisateur existant en base de donn�e
	 * @param password Mot de passe en clair de l'utilisateur
	 */
	public User getByPseudoPass(String pseudo, String password);
}
