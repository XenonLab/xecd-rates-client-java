package com.xe.xecdApiClient.model;

public class ToRateMonth
{
	private Double monthlyAverage;
	private Double monthlyAverageInverse;
	private Integer month;
	private Integer daysInMonth;

	public Double getMonthlyAverageInverse()
	{
		return monthlyAverageInverse;
	}

	public void setInverseMonthlyAverage (Double monthlyAverageInverse)
	{
		this.monthlyAverageInverse = monthlyAverageInverse;
	}

	public ToRateMonth()
	{
		//
	}

	public void setMonth(Integer month)
	{
		this.month = month;
	}
	public Integer getMonth()
	{
		return month;
	}
	public Double getMonthlyAverage()
	{
		return monthlyAverage;
	}
	public void setMonthlyAverage(Double monthlyAverage)
	{
		this.monthlyAverage = monthlyAverage;
	}
	public Integer getDaysInMonth()
	{
		return daysInMonth;
	}
	public void setDaysInMonth(Integer daysInMonth)
	{
		this.daysInMonth = daysInMonth;
	}
}
