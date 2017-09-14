package com.xe.xecdApiClient.model;


public class Rate
{
	private String quotecurrency;
	private Double mid;
	
	public Rate()
	{
		//default
	}
	
	public Rate(String quotecurrency, Double mid)
	{
		this.quotecurrency = quotecurrency;
		this.mid = mid;
	}
	
	public String getQuotecurrency()
	{
		return quotecurrency;
	}
	public void setQuotecurrency(String quotecurrency)
	{
		this.quotecurrency = quotecurrency;
	}
	public Double getMid()
	{
		return mid;
	}
	public void setMid(Double mid)
	{
		this.mid = mid;
	}
}
