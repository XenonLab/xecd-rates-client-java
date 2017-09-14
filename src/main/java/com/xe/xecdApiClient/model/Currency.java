package com.xe.xecdApiClient.model;

public class Currency 
{
	private String iso;
	private String currency_name;
	private Boolean is_obsolete;
	private String superseded_by;
	private String currency_symbol;
	private String currency_symbol_on_right;
	
	public String getIso() 
	{
		return iso;
	}
	
	public void setIso(String iso) 
	{
		this.iso = iso;
	}
	
	public String getCurrency_name() 
	{
		return currency_name;
	}
	
	public void setCurrency_name(String currency_name) 
	{
		this.currency_name = currency_name;
	}
	
	public Boolean getIs_obsolete() 
	{
		return is_obsolete;
	}
	
	public void setIs_obsolete(Boolean is_obsolete) 
	{
		this.is_obsolete = is_obsolete;
	}

	public String getSuperseded_by() 
	{
		return superseded_by;
	}

	public void setSuperseded_by(String superseded_by) 
	{
		this.superseded_by = superseded_by;
	}

	public String getCurrency_symbol()
	{
		return currency_symbol;
	}

	public void setCurrency_symbol(String currency_symbol)
	{
		this.currency_symbol = currency_symbol;
	}

	public String getCurrency_symbol_on_right()
	{
		return currency_symbol_on_right;
	}

	public void setCurrency_symbol_on_right(String currency_symbol_on_right)
	{
		this.currency_symbol_on_right = currency_symbol_on_right;
	}
}
