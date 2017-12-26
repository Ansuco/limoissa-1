package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.utils.Redirect;

public abstract class AbstractAction
{
	public abstract Redirect executeAction(HttpServletRequest request);
}
