package com.crexos.model.utils;

public final class Redirect
{
	public boolean redirect = false;
	public String action = "home";
	//TODO : Am�liorer concept de redirection car pour la fonctionnalit� EDIT AUTHOR en GET l'�criture de la redirection est authors:edit et en POST authors/read?author-id=1 (subtilit� sur : ou /)
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
