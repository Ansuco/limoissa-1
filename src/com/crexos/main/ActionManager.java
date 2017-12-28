package com.crexos.main;

import java.util.HashMap;
import java.util.Map;

import com.crexos.main.utils.AbstractAction;
import com.crexos.main.utils.AddAuthor;
import com.crexos.main.utils.AddBook;
import com.crexos.main.utils.DeleteAuthor;
import com.crexos.main.utils.DeleteBook;
import com.crexos.main.utils.EditAuthor;
import com.crexos.main.utils.EditBook;
import com.crexos.main.utils.HomeBook;
import com.crexos.main.utils.ListAuthors;
import com.crexos.main.utils.ListBooks;
import com.crexos.main.utils.LoginAction;
import com.crexos.main.utils.LogoutAction;
import com.crexos.main.utils.ReadAuthor;
import com.crexos.main.utils.ReadBook;
import com.crexos.main.utils.SigninAction;

public class ActionManager
{
	public static final String ACTION_HOME = "home";
	public static final String ACTION_ADD_BOOK = "books:add";
	public static final String ACTION_DELETE_BOOK = "books:delete";
	public static final String ACTION_EDIT_BOOK = "books:edit";
	public static final String ACTION_READ_BOOK = "books:read";
	public static final String ACTION_LIST_BOOKS = "books";
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
		
		actions.put(ACTION_HOME, new HomeBook());
		actions.put(ACTION_ADD_BOOK, new AddBook());
		actions.put(ACTION_DELETE_BOOK, new DeleteBook());
		actions.put(ACTION_EDIT_BOOK, new EditBook());
		actions.put(ACTION_READ_BOOK, new ReadBook());
		actions.put(ACTION_LIST_BOOKS, new ListBooks());
		
		actions.put(ACTION_LOGIN, new LoginAction());
		actions.put(ACTION_SIGNIN, new SigninAction());
		actions.put(ACTION_LOGOUT, new LogoutAction());
		
		actions.put(ACTION_LIST_AUTHORS, new ListAuthors());
		actions.put(ACTION_EDIT_AUTHOR, new EditAuthor());
		actions.put(ACTION_DELETE_AUTHOR, new DeleteAuthor());
		actions.put(ACTION_ADD_AUTHOR, new AddAuthor());
		actions.put(ACTION_READ_AUTHOR, new ReadAuthor());
	}
	
	public static AbstractAction getAction(String actionName)
	{
		return actions.get(actionName);
	}
}
