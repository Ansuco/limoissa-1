package com.crexos.main;

import java.util.HashMap;
import java.util.Map;

import com.crexos.main.utils.AbstractAction;
import com.crexos.main.utils.AddBook;
import com.crexos.main.utils.DeleteBook;
import com.crexos.main.utils.EditBook;
import com.crexos.main.utils.HomeBook;
import com.crexos.main.utils.ListBooks;
import com.crexos.main.utils.LoginAction;

public class ActionManager
{
	public static final String ACTION_HOME = "home";
	public static final String ACTION_ADD = "add";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_LIST_BOOKS = "books";
	public static final String ACTION_LOGIN = "login";
	
	private static Map<String, AbstractAction> actions;
	static
	{
		actions = new HashMap<String, AbstractAction>();
		
		actions.put(ACTION_HOME, new HomeBook());
		actions.put(ACTION_ADD, new AddBook());
		actions.put(ACTION_DELETE, new DeleteBook());
		actions.put(ACTION_EDIT, new EditBook());
		actions.put(ACTION_LIST_BOOKS, new ListBooks());
		
		actions.put(ACTION_LOGIN, new LoginAction());
	}
	
	public static AbstractAction getAction(String actionName)
	{
		return actions.get(actionName);
	}
}
