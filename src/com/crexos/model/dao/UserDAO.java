package com.crexos.model.dao;

import com.crexos.model.beans.User;

public interface UserDAO extends InterfaceDAO<User>
{
	public User getByPseudoPass(String pseudo, String password);
}
