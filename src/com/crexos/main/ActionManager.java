package com.crexos.main;

import java.util.HashMap;
import java.util.Map;

import com.crexos.main.utils.AbstractAction;
import com.crexos.main.utils.HomeBook;
import com.crexos.main.utils.LogoutAction;
import com.crexos.model.jpa.action.CatalogAction;
import com.crexos.model.jpa.action.DeleteAuthor;
import com.crexos.model.jpa.action.DeleteBook;
import com.crexos.model.jpa.action.ListAuthors;
import com.crexos.model.jpa.action.ListBooks;
import com.crexos.model.jpa.action.LoginAction;
import com.crexos.model.jpa.action.ReadAuthor;
import com.crexos.model.jpa.action.ReadBook;
import com.crexos.model.jpa.action.SigninAction;

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
//		actions.put(ACTION_HOME, new HomeBook());
//		actions.put(ACTION_MAIN, new CatalogAction());
//		actions.put(ACTION_ADD_BOOK, new AddBook());
//		actions.put(ACTION_DELETE_BOOK, new DeleteBook());
//		actions.put(ACTION_EDIT_BOOK, new EditBook());
//		actions.put(ACTION_READ_BOOK, new ReadBook());
//		actions.put(ACTION_LIST_BOOKS, new ListBooks());
//		actions.put(ACTION_IMPORT_BOOKS, new ImportBooks());
//		actions.put(ACTION_DELETEJOIN_BOOK, new DeleteJoinAuthorBook());
//		actions.put(ACTION_ADDAUTHORS_BOOK, new AddAuthorsBook());
//		
//		actions.put(ACTION_LOGIN, new LoginAction());
//		actions.put(ACTION_SIGNIN, new SigninAction());
//		actions.put(ACTION_LOGOUT, new LogoutAction());
//		
//		actions.put(ACTION_LIST_AUTHORS, new ListAuthors());
//		actions.put(ACTION_EDIT_AUTHOR, new EditAuthor());
//		actions.put(ACTION_DELETE_AUTHOR, new DeleteAuthor());
//		actions.put(ACTION_ADD_AUTHOR, new AddAuthor());
//		actions.put(ACTION_READ_AUTHOR, new ReadAuthor());
		
//Hibernate Actions
		actions.put(ACTION_HOME, new HomeBook());
		actions.put(ACTION_LOGIN, new LoginAction());
		actions.put(ACTION_SIGNIN, new SigninAction());
		actions.put(ACTION_LOGOUT, new LogoutAction());
		
		actions.put(ACTION_MAIN, new CatalogAction());
		actions.put(ACTION_READ_BOOK, new ReadBook());
		actions.put(ACTION_LIST_BOOKS, new ListBooks());
		actions.put(ACTION_DELETE_BOOK, new DeleteBook());
		
		actions.put(ACTION_LIST_AUTHORS, new ListAuthors());
		actions.put(ACTION_READ_AUTHOR, new ReadAuthor());
		actions.put(ACTION_DELETE_AUTHOR, new DeleteAuthor());
	}
	
	public static AbstractAction getAction(String actionName)
	{
		return actions.get(actionName);
	}
}
