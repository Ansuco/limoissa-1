package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractAction
{
	public abstract boolean executeAction(HttpServletRequest request);
}
