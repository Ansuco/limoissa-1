package com.crexos.main;

import java.util.HashMap;
import java.util.Map;

import com.crexos.main.utils.AbstractAction;

public class ActionManager
{
	public static final String ACTION_HOME = "home";
	public static final String ACTION_MAIN = "catalog";
	public static final String ACTION_ADD_BOOK = "books:add";
	public static final String ACTION_DELETE_BOOK = "books:delete";
	public static final String ACTION_EDIT_BOOK = "books:edit";
	public static final String ACTION_READ_BOOK = "books:read";
	public static final String ACTION_LIST_BOOKS = "books";
	public static final String ACTION_IMPORT_BOOKS = "importbooks";
	public static final String ACTION_DELETEJOIN_BOOK = "books:deletejoin";
	public static final String ACTION_ADDAUTHORS_BOOK = "books:addauthors";
	public static final String ACTION_LIST_AUTHORS = "authors";
	public static final String ACTION_EDIT_AUTHOR = "authors:edit";
	public static final String ACTION_ADD_AUTHOR = "authors:add";
	public static final String ACTION_DELETE_AUTHOR = "authors:delete";
	public static final String ACTION_READ_AUTHOR = "authors:read";
	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_SIGNIN = "signin";
	public static final String ACTION_LOGOUT = "logout";
	
	private static Map<String, AbstractAction> actions;
	static
	{
		actions = new HashMap<String, AbstractAction>();
//DAO Actions		
//		actions.put(ACTION_HOME, new com.crexos.main.utils.HomeBook());
//		actions.put(ACTION_MAIN, new com.crexos.main.utils.CatalogAction());
//		actions.put(ACTION_ADD_BOOK, new com.crexos.main.utils.AddBook());
//		actions.put(ACTION_DELETE_BOOK, new com.crexos.main.utils.DeleteBook());
//		actions.put(ACTION_EDIT_BOOK, new com.crexos.main.utils.EditBook());
//		actions.put(ACTION_READ_BOOK, new com.crexos.main.utils.ReadBook());
//		actions.put(ACTION_LIST_BOOKS, new com.crexos.main.utils.ListBooks());
//		actions.put(ACTION_IMPORT_BOOKS, new com.crexos.main.utils.ImportBooks());
//		actions.put(ACTION_DELETEJOIN_BOOK, new com.crexos.main.utils.DeleteJoinAuthorBook());
//		actions.put(ACTION_ADDAUTHORS_BOOK, new com.crexos.main.utils.AddAuthorsBook());
//		
//		actions.put(ACTION_LOGIN, new com.crexos.main.utils.LoginAction());
//		actions.put(ACTION_SIGNIN, new com.crexos.main.utils.SigninAction());
//		actions.put(ACTION_LOGOUT, new com.crexos.main.utils.LogoutAction());
//		
//		actions.put(ACTION_LIST_AUTHORS, new com.crexos.main.utils.ListAuthors());
//		actions.put(ACTION_EDIT_AUTHOR, new com.crexos.main.utils.EditAuthor());
//		actions.put(ACTION_DELETE_AUTHOR, new com.crexos.main.utils.DeleteAuthor());
//		actions.put(ACTION_ADD_AUTHOR, new com.crexos.main.utils.AddAuthor());
//		actions.put(ACTION_READ_AUTHOR, new com.crexos.main.utils.ReadAuthor());
		
//Hibernate Actions
		actions.put(ACTION_HOME, new com.crexos.main.utils.HomeBook());
		actions.put(ACTION_LOGIN, new com.crexos.model.jpa.action.LoginAction());
		actions.put(ACTION_SIGNIN, new com.crexos.model.jpa.action.SigninAction());
		actions.put(ACTION_LOGOUT, new com.crexos.main.utils.LogoutAction());
		
		actions.put(ACTION_MAIN, new com.crexos.model.jpa.action.CatalogAction());
		actions.put(ACTION_READ_BOOK, new com.crexos.model.jpa.action.ReadBook());
		actions.put(ACTION_LIST_BOOKS, new com.crexos.model.jpa.action.ListBooks());
		actions.put(ACTION_ADD_BOOK, new com.crexos.model.jpa.action.AddBook());
		actions.put(ACTION_EDIT_BOOK, new com.crexos.model.jpa.action.EditBook());
		actions.put(ACTION_IMPORT_BOOKS, new com.crexos.main.utils.ImportBooks());
		actions.put(ACTION_DELETE_BOOK, new com.crexos.model.jpa.action.DeleteBook());
		
		actions.put(ACTION_ADD_AUTHOR, new com.crexos.model.jpa.action.AddAuthor());
		actions.put(ACTION_LIST_AUTHORS, new com.crexos.model.jpa.action.ListAuthors());
		actions.put(ACTION_READ_AUTHOR, new com.crexos.model.jpa.action.ReadAuthor());
		actions.put(ACTION_EDIT_AUTHOR, new com.crexos.model.jpa.action.EditAuthor());
		actions.put(ACTION_DELETE_AUTHOR, new com.crexos.model.jpa.action.DeleteAuthor());
	}
	
	public static AbstractAction getAction(String actionName)
	{
		return actions.get(actionName);
	}
}
