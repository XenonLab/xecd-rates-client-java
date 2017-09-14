package com.xe.xecdApiClient.model;

import java.util.Date;
import java.util.List;

public class ConvertFromResponse 
{
	private String terms;
	private String privacy;
	private String from;
	private Double amount;
	private Date timestamp;
	private List<Rate> to;

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

	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	public List<Rate> getTo()
	{
		return to;
	}

	public void setTo(List<Rate> to)
	{
		this.to = to;
	}
}
