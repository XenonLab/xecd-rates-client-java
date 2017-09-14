package com.xe.xecdApiClient.model;

import java.util.List;
import java.util.Map;

public class MonthlyAverageResponse
{
	/**
	 * 
	 */
	private String terms;
	private String privacy;
	private String from;
	private Double amount;
	private Integer year;
	private Map<String, List<ToRateMonth>> to;

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
	public Integer getYear()
	{
		return year;
	}
	public void setYear(Integer year)
	{
		this.year = year;
	}
	public Map<String, List<ToRateMonth>> getTo()
	{
		return to;
	}
	public void setTo(Map<String, List<ToRateMonth>> to)
	{
		this.to = to;
	}
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
	public void setLegal(String legal)
	{
		this.privacy = legal;
	}
}
