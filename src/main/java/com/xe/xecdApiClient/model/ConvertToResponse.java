package com.xe.xecdApiClient.model;

import java.util.Date;
import java.util.List;

public class ConvertToResponse 
{
	private String terms;
	private String privacy;
	private String to;
	private Double amount;
	private Date timestamp;
	private List<Rate> from;
	
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

	public String getTo() 
	{
		return to;
	}
	
	public void setTo(String to) 
	{
		this.to = to;
	}
	
	public Double getAmount() 
	{
		return amount;
	}
	
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}
	
	public Date getTimestamp() 
	{
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) 
	{
		this.timestamp = timestamp;
	}
	
	public List<Rate> getFrom() 
	{
		return from;
	}
	
	public void setFrom(List<Rate> from) 
	{
		this.from = from;
	}
}
