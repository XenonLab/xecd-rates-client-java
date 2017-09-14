package com.xe.xecdApiClient.model;

import java.util.List;

public class CurrenciesResponse 
{
	private String terms;
	private String privacy;
	private List<Currency> currencies;

	public String getTerms()
	{
		return terms;
	}

	public void setTerms(String terms)
	{
		this.terms = terms;
	}

	public String getPrivacy()
	{
		return privacy;
	}

	public void setPrivacy(String privacy)
	{
		this.privacy = privacy;
	}

	public List<Currency> getCurrencies() 
	{
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) 
	{
		this.currencies = currencies;
	}
}
