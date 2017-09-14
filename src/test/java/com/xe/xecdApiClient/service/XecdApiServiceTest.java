package com.xe.xecdApiClient.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import com.xe.xecdApiClient.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xe.xecdApiClient.config.XecdApiConfigBean;
import com.xe.xecdApiClient.exception.XecdApiException;

public class XecdApiServiceTest
{
	@Mock
	private XecdHttpClientImpl wsClient;

	private XecdApiConfigBean xecdApiConfigBean;

	private XecdApiServiceImpl apiService;

	private JsonObject testData;

	@Before
	public void init() throws XecdApiException
	{
		MockitoAnnotations.initMocks(this);
		xecdApiConfigBean = new XecdApiConfigBean();
		xecdApiConfigBean.setServerPrefix(System.getenv("XECD_SERVER_PREFIX"));
		xecdApiConfigBean.setAccountId(System.getenv("XECD_ACCOUNT_ID"));
		xecdApiConfigBean.setApiKey(System.getenv("XECD_API_KEY"));
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			apiService = new XecdApiServiceImpl(xecdApiConfigBean, wsClient);
		}
		else
		{
			apiService = new XecdApiServiceImpl(xecdApiConfigBean);
		}

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("sample-data.json");
		InputStreamReader reader = new InputStreamReader(in);
		JsonParser parser = new JsonParser();
		testData = parser.parse(reader).getAsJsonObject();
	}
	
	@Test
	public void testValidAccountInfo() throws URISyntaxException, IOException, XecdApiException
	{
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			Mockito.when(wsClient.getResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
					.thenReturn(testData.get("AccountInfo").toString());
		}
		AccountInfoResponse response = apiService.getAccountInfo(xecdApiConfigBean.getAccountId(), xecdApiConfigBean.getApiKey());
		Assert.assertNotNull(response);
		response = apiService.getAccountInfo();
		Assert.assertNotNull(response);
	}

	@Test
	public void testValidCurrencies() throws URISyntaxException, IOException, XecdApiException
	{
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			Mockito.when(wsClient.getResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
					.thenReturn(testData.get("AccountInfo").toString());
		}
		
		CurrenciesResponse response = apiService.getCurrencies(xecdApiConfigBean.getAccountId(), xecdApiConfigBean.getApiKey(), true, "en", null);
		Assert.assertNotNull(response);
		response = apiService.getCurrencies(true, "en", null);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testValidConvertFrom() throws URISyntaxException, IOException, XecdApiException
	{
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			Mockito.when(wsClient.getResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
					.thenReturn(testData.get("AccountInfo").toString());
		}

		ConvertFromResponse response = apiService.convertFrom(xecdApiConfigBean.getAccountId(), xecdApiConfigBean.getApiKey(), "CAD", "EUR", 1.00, false, false);
		Assert.assertNotNull(response);
		response = apiService.convertFrom("CAD", "EUR", 1.00, false, false);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testValidConvertTo() throws URISyntaxException, IOException, XecdApiException
	{
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			Mockito.when(wsClient.getResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
					.thenReturn(testData.get("AccountInfo").toString());
		}

		ConvertToResponse response = apiService.convertTo(xecdApiConfigBean.getAccountId(), xecdApiConfigBean.getApiKey(), "CAD", "EUR", 1.00, false, false);
		Assert.assertNotNull(response);
		response = apiService.convertTo("CAD", "EUR", 1.00, false, false);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testValidHistoricRate() throws URISyntaxException, IOException, XecdApiException
	{
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			Mockito.when(wsClient.getResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
					.thenReturn(testData.get("AccountInfo").toString());
		}
		
		HistoricRateResponse response = apiService.historicRate(xecdApiConfigBean.getAccountId(), xecdApiConfigBean.getApiKey(), "CAD", "EUR,JPY", "2011-03-05", null, 1.00, false, false);
		Assert.assertNotNull(response);
		response = apiService.historicRate("CAD", "EUR,JPY", "2011-03-05", null, 1.00, false, false);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testValidHistoricRatePeriod() throws URISyntaxException, IOException, XecdApiException
	{
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			Mockito.when(wsClient.getResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
					.thenReturn(testData.get("AccountInfo").toString());
		}
		
		HistoricRatePeriodResponse response = apiService.historicRatePeriod(xecdApiConfigBean.getAccountId(), xecdApiConfigBean.getApiKey(), "USD", "CAD,EUR", 1.00, "2011-02-11T12:00", "2011-06-02T12:00", "daily", 1, 500, null, null);
		Assert.assertNotNull(response);
		response = apiService.historicRatePeriod("USD", "CAD,EUR", 1.00, "2011-02-11T12:00", "2011-06-02T12:00", null, null, null, null, null);
		Assert.assertNotNull(response);
	}

	@Test
	public void testMonthlyAverage() throws URISyntaxException, IOException, XecdApiException
	{
		if (xecdApiConfigBean.getServerPrefix() == null || xecdApiConfigBean.getAccountId() == null || xecdApiConfigBean.getApiKey() == null)
		{
			Mockito.when(wsClient.getResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
					.thenReturn(testData.get("AccountInfo").toString());
		}

		MonthlyAverageResponse response = apiService.monthlyAverage(xecdApiConfigBean.getAccountId(), xecdApiConfigBean.getApiKey(), "USD", "CAD", 1.00, 2016, 6,  null, null);
		Assert.assertNotNull(response);
		response = apiService.monthlyAverage("USD", "CAD", 1.00, 2016, 6,  null, null);
		Assert.assertNotNull(response);
	}

	// ***********************************************************
	// The following tests will not throw the expected exceptions
	// unless the environment variables are specified.
	// This is because the client calls are mocked away if the
	// config is not set up with the environment variables
	// ***********************************************************

	@Test
	public void testInvalidAccountInfo() throws XecdApiException
	{
		try
		{
			apiService.getAccountInfo("invalid", "invalid");
		}
		catch(XecdApiException e)
		{
			Assert.assertEquals(1,e.getErrorResponse().getCode());
			Assert.assertEquals("Bad credentials", e.getErrorResponse().getMessage());
		}
	}

	@Test
	public void testInvalidConvertFrom() throws XecdApiException
	{
		try
		{
			apiService.convertFrom("ZZZ","YYY",1.0,false,false);
		}
		catch(XecdApiException e)
		{
			Assert.assertEquals(7, e.getErrorResponse().getCode());
		}
	}

	@Test
	public void testInvalidConvertTo() throws XecdApiException
	{
		try
		{
			apiService.convertTo("ZZZ","YYY",1.0,false,false);
		}
		catch(XecdApiException e)
		{
			Assert.assertEquals(7, e.getErrorResponse().getCode());
		}
	}

	@Test
	public void testInvalidHistoricRate() throws XecdApiException
	{
		try
		{
			apiService.historicRate("SZL","CAD","1980-06-27","20:06",1.0,false,false);
		}
		catch(XecdApiException e)
		{
			Assert.assertEquals(8, e.getErrorResponse().getCode());
		}
	}

	@Test
	public void testInvalidHistoricRatePeriod() throws XecdApiException
	{
		try
		{
			apiService.historicRatePeriod("CAD","USD",1.0,"1000-10-10","2000-10-10",null,null,null,false,false);
		}
		catch(XecdApiException e)
		{
			Assert.assertEquals(10, e.getErrorResponse().getCode());
		}
	}

	@Test
	public void testInvalidMonthlyAverage() throws XecdApiException
	{
		try
		{
			apiService.monthlyAverage("CAD","USD",1.0,3000,01,false,false);
		}
		catch(XecdApiException e)
		{
			Assert.assertEquals(14, e.getErrorResponse().getCode());
		}
		try
		{
			apiService.monthlyAverage("CAD","USD",1.0,2000,21,false,false);
		}
		catch(XecdApiException e)
		{
			Assert.assertEquals(15, e.getErrorResponse().getCode());
		}
	}

}
