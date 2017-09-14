package com.xe.xecdApiClient.model;

import java.util.List;
import java.util.Map;

public class HistoricRatePeriodResponse 
{
	private String terms;
	private String privacy;
	private String from;
	private Double amount;
	private Map<String, List<HistoricRate>> to;
	
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
	public String getFrom()
	{
		return from;
	}
	public void setFrom(String from)
	{
		this.from = from;
	}
	public Double getAmount()
	{
		return amount;
	}
	public void setAmount(Double amount)
	{
		this.amount = amount;
	}
	public Map<String, List<HistoricRate>> getTo()
	{
		return to;
	}
	public void setTo(Map<String, List<HistoricRate>> to)
	{
		this.to = to;
	}
}
