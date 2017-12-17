package com.crexos.main;

import java.util.HashMap;
import java.util.Map;

import com.crexos.main.utils.AbstractAction;
import com.crexos.main.utils.AddBook;
import com.crexos.main.utils.DeleteBook;
import com.crexos.main.utils.EditBook;
import com.crexos.main.utils.ListBooks;

public class ActionManager
{
	private static Map<String, AbstractAction> actions;
	static
	{
		actions = new HashMap<String, AbstractAction>();
		
		actions.put(FrontServlet.ACTION_ADD, new AddBook());
		actions.put(FrontServlet.ACTION_DELETE, new DeleteBook());
		actions.put(FrontServlet.ACTION_EDIT, new EditBook());
		actions.put(FrontServlet.ACTION_LIST_BOOKS, new ListBooks());
	}
	
	public static AbstractAction getAction(String actionName)
	{
		return actions.get(actionName);
	}
}
