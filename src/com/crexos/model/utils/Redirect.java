package com.crexos.model.utils;

public final class Redirect
{
	public boolean redirect = false;
	public String action = "home";
	
	public Redirect(){}
	
	public Redirect(boolean redirect, String redirection)
	{
		this.redirect = redirect;
		this.action = redirection;
	}
	
	public Redirect(String redirection)
	{
		this(false, redirection);
	}
	
	public boolean isRedirection()
	{
		return this.redirect;
	}
	
	public String getAction()
	{
		return this.action;
	}
}
