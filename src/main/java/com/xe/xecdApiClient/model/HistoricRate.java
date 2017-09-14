package com.xe.xecdApiClient.model;

import java.util.Date;

public class HistoricRate 
{
	private String terms;
	private String privacy;
	private Double mid;
	private Date timestamp;
	
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

	public Double getMid() 
	{
		return mid;
	}
	
	public void setMid(Double mid) 
	{
		this.mid = mid;
	}
	
	public Date getTimestamp() 
	{
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}
}
