package com.crexos.model.utils;

public final class Redirect
{
	public boolean redirect = false;
	public String action = "home";
	//TODO : Améliorer concept de redirection car pour la fonctionnalité EDIT AUTHOR en GET l'écriture de la redirection est authors:edit et en POST authors/read?author-id=1 (subtilité sur : ou /)
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
