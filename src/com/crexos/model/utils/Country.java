package com.crexos.model.utils;

public enum Country
{
	AFRIQUE("Afrique"),
	BRESIL("Brésil"),
	ESPAGNE("Espagne"),
	ETAT_UNIS("Etat-Unis"),
	FRANCE("France"),
	JAPON("Japon"),
	MARTINIQUE("Martinique"),
	MEXIQUE("Mexique");

	private String name;
	
	private Country(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
