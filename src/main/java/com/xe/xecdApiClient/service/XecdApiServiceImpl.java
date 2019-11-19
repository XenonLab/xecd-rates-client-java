package com.xe.xecdApiClient.service;

import java.text.MessageFormat;

import com.xe.xecdApiClient.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xe.xecdApiClient.config.XecdApiConfigBean;
import com.xe.xecdApiClient.exception.XecdApiException;
import com.xe.xecdApiClient.utils.JsonUtils;

public class XecdApiServiceImpl implements XecdApiService
{

	private Logger logger = LoggerFactory.getLogger(XecdApiServiceImpl.class);

	private String accountId;
	private String apiKey;
	private String serverPrefix = "https://xecdapi.xe.com";

	private static String accountInfoUrl = "{0}/v1/account_info";
	private static String currenciesUrl = "{0}/v1/currencies/?";
	private static String convertFromUrl = "{0}/v1/convert_from?to={1}";
	private static String convertToUrl = "{0}/v1/convert_to?from={1}";
	private static String historicRateUrl = "{0}/v1/historic_rate/?to={1}&date={2}";
	private static String historicRatePeriodUrl = "{0}/v1/historic_rate/period/?to={1}";
	private static String monthlyAverageUrl = "{0}/v1/monthly_average?to={1}";

	private JsonUtils jsonUtils;

	private final XecdHttpClient wsClient;

	/**
	 * Allows specification of mock http client for testing purposes
	 *
	 * @param config
	 * @param client
	 */
	XecdApiServiceImpl(XecdApiConfigBean config, XecdHttpClientImpl client)
	{
		logger.debug("INITIALIZING WITH CONFIG BEAN: {} AND HTTPCLIENT: {}", config, client);

		this.accountId = config.getAccountId();
		this.apiKey = config.getApiKey();
		this.serverPrefix = config.getServerPrefix() != null ? config.getServerPrefix() : this.serverPrefix;
		this.jsonUtils = new JsonUtils();
		this.wsClient = client;
	}

	/**
	 * Create an XecdAPIConfigBean then use it here Spring @ConfigurationProperties can help using a prefix for XecdAPIClient properties
	 *
	 * @param config
	 */
	XecdApiServiceImpl(XecdApiConfigBean config)
	{
		logger.debug("INITIALIZING WITH CONFIG BEAN: {}", config);

		this.accountId = config.getAccountId();
		this.apiKey = config.getApiKey();
		this.serverPrefix = config.getServerPrefix() != null ? config.getServerPrefix() : this.serverPrefix;
		this.jsonUtils = new JsonUtils();
		this.wsClient = new XecdHttpClientImpl(config.getConnectTimeout(), config.useSystemProperties());
	}

	/**
	 * Looks at the environment variables to satisfy required properties
	 * - XECD_ACCOUNT_ID
	 * - XECD_API_KEY
	 * @throws XecdApiException
	 */
	XecdApiServiceImpl() throws XecdApiException
	{
		String apiKeyVar = System.getenv("XECD_API_KEY");
		String accountIdVar = System.getenv("XECD_ACCOUNT_ID");

		this.wsClient = new XecdHttpClientImpl(null, null);

		if(apiKeyVar != null && accountIdVar != null && !apiKeyVar.isEmpty() && !accountIdVar.isEmpty())
		{
			logger.debug("INITIALIZING WITH ENVIRONMENT VARIABLES");

			this.apiKey = System.getenv("XECD_API_KEY");
			this.accountId = System.getenv("XECD_ACCOUNT_ID");
			this.jsonUtils = new JsonUtils();

		}
		else
		{
			throw new XecdApiException("${XECD_ACCOUNT_ID} and ${XECD_API_KEY} need to be non-null if you use the default constructor");
		}
	}

	@Override
	public AccountInfoResponse getAccountInfo(String accountId, String apiKey) throws XecdApiException
	{
		AccountInfoResponse response = null;

		try
		{
			String accountInfoUrlStr = MessageFormat.format(accountInfoUrl, serverPrefix);
			logger.debug("Calling {}", accountInfoUrlStr);
			String responseString = wsClient.getResponse(accountInfoUrlStr, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, AccountInfoResponse.class);
		}
		catch(XecdApiException e)
		{
			logger.error("Error getting account information from XECD API", e);
			throw new XecdApiException("Error getting account information from XECD API", e, e.getErrorResponse(), e.getHttpStatusCode());
		}
		catch(Exception e)
		{
			logger.error("Error getting account information from XECD API", e);
			throw new XecdApiException("Error getting account information from XECD API", e);
		}

		return response;
	}

	@Override
	public AccountInfoResponse getAccountInfo() throws XecdApiException
	{
		return getAccountInfo(accountId, apiKey);
	}

	@Override
	public CurrenciesResponse getCurrencies(String accountId, String apiKey, Boolean obsolete, String language, String iso) throws XecdApiException
	{
		CurrenciesResponse response = null;

		String currenciesString = MessageFormat.format(currenciesUrl, serverPrefix);
		currenciesString += (obsolete != null) ? "obsolete=" + obsolete.toString() : "";
		currenciesString += (language != null && !language.isEmpty()) ? "&language=" + language : "";
		currenciesString += (iso != null && !iso.isEmpty()) ? "&iso=" + iso : "";

		try
		{
			logger.debug("Calling {}", currenciesString);
			String responseString = wsClient.getResponse(currenciesString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, CurrenciesResponse.class);
		}
		catch(XecdApiException e)
		{
			logger.error("Error getting currencies from XECD API", e);
			throw new XecdApiException("Error getting currencies from XECD API", e, e.getErrorResponse(), e.getHttpStatusCode());
		}
		catch(Exception e)
		{
			logger.error("Error getting currencies from XECD API", e);
			throw new XecdApiException("Error getting currencies from XECD API", e);
		}

		return response;
	}

	@Override
	public CurrenciesResponse getCurrencies(Boolean obsolete, String language, String iso) throws XecdApiException
	{
		return getCurrencies(accountId, apiKey, obsolete, language, iso);
	}

	@Override
	public CurrenciesResponse getCurrencies(String accountId, String apiKey, Boolean obsolete, String language) throws XecdApiException
	{
		return getCurrencies(accountId, apiKey, obsolete, language, null);
	}

	@Override
	public CurrenciesResponse getCurrencies(Boolean obsolete, String language) throws XecdApiException
	{
		return getCurrencies(obsolete, language, null);
	}


	@Override
	public ConvertFromResponse convertFrom(String accountId, String apiKey, String from, String to, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		ConvertFromResponse response = null;

		String convertFromString = MessageFormat.format(convertFromUrl, serverPrefix, to);

		convertFromString += (from != null && !from.isEmpty()) ? "&from=" + from : "";
		convertFromString += (amount != null && amount != 0) ? "&amount=" + amount : "";
		convertFromString += (obsolete != null) ? "&obsolete=" + obsolete.toString() : "";
		convertFromString += (inverse != null) ? "&inverse=" + inverse.toString() : "";

		try
		{
			logger.debug("Calling {}", convertFromString);

			String responseString = wsClient.getResponse(convertFromString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, ConvertFromResponse.class);
		}
		catch(XecdApiException e)
		{
			logger.error("Error getting response from XECD API", e);
			throw new XecdApiException("Error getting response from XECD API", e, e.getErrorResponse(), e.getHttpStatusCode());
		}
		catch(Exception e)
		{
			logger.error("Error getting response from XECD API", e);
			throw new XecdApiException("Error getting response from XECD API", e);
		}
		return response;
	}

	@Override
	public ConvertFromResponse convertFrom(String from, String to, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		return convertFrom(accountId, apiKey, from, to, amount, obsolete, inverse);
	}

	@Override
	public ConvertFromResponse convertFrom(String accountId, String apiKey, String from, String to, Double amount, Boolean obsolete) throws XecdApiException
	{
		return convertFrom(accountId, apiKey, from, to, amount, obsolete, null);
	}

	@Override
	public ConvertFromResponse convertFrom(String from, String to, Double amount, Boolean obsolete) throws XecdApiException
	{
		return convertFrom(from, to, amount, obsolete, null);
	}

	@Override
	public ConvertToResponse convertTo(String accountId, String apiKey, String to, String from, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		ConvertToResponse response = null;

		String convertToString = MessageFormat.format(convertToUrl, serverPrefix, from);

		convertToString += (to != null && !to.isEmpty()) ? "&to=" + to : "";
		convertToString += (amount != null && amount != 0) ? "&amount=" + amount : "";
		convertToString += (obsolete != null) ? "&obsolete=" + obsolete.toString() : "";
		convertToString += (inverse != null) ? "&inverse=" + inverse.toString() : "";

		try
		{
			logger.debug("Calling {}", convertToString);

			String responseString = wsClient.getResponse(convertToString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, ConvertToResponse.class);
		}
		catch(XecdApiException e)
		{
			logger.error("Error getting response from XECD API", e);
			throw new XecdApiException("Error getting response from XECD API", e, e.getErrorResponse(), e.getHttpStatusCode());
		}
		catch(Exception e)
		{
			logger.error("Error getting response from XECD API", e);
			throw new XecdApiException("Error getting response from XECD API", e);
		}
		return response;
	}

	@Override
	public ConvertToResponse convertTo(String to, String from, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		return convertTo(accountId, apiKey, to, from, amount, obsolete, inverse);
	}

	@Override
	public ConvertToResponse convertTo(String accountId, String apiKey, String to, String from, Double amount, Boolean obsolete) throws XecdApiException
	{
		return convertTo(accountId, apiKey, to, from, amount, obsolete, null);
	}

	@Override
	public ConvertToResponse convertTo(String to, String from, Double amount, Boolean obsolete) throws XecdApiException
	{
		return convertTo( to, from, amount, obsolete, null);
	}

	@Override
	public HistoricRateResponse historicRate(String accountId, String apiKey, String from, String to, String date, String time, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		HistoricRateResponse response = null;

		String historicRateString = MessageFormat.format(historicRateUrl, serverPrefix, to, date);

		historicRateString += (from != null && !from.isEmpty()) ? "&from=" + from : "";
		historicRateString += (time != null && !time.isEmpty()) ? "&time=" + time : "";
		historicRateString += (amount != null && amount != 0) ? "&amount=" + amount : "";
		historicRateString += (obsolete != null) ? "&obsolete=" + obsolete.toString() : "";
		historicRateString += (inverse != null) ? "&inverse=" + inverse.toString() : "";

		try
		{
			logger.debug("Calling {}", historicRateString);
			String responseString = wsClient.getResponse(historicRateString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, HistoricRateResponse.class);
		}
		catch(XecdApiException e)
		{
			logger.error("Error getting historic rates from XECD API", e);
			throw new XecdApiException("Error getting historic rates from XECD API", e, e.getErrorResponse(), e.getHttpStatusCode());
		}
		catch(Exception e)
		{
			logger.error("Error getting historic rates from XECD API", e);
			throw new XecdApiException("Error getting historic rates from XECD API", e);
		}


		return response;
	}

	@Override
	public HistoricRateResponse historicRate(String from, String to, String date, String time, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		return historicRate(accountId, apiKey, from, to, date, time, amount, obsolete, inverse);
	}

	@Override
	public HistoricRateResponse historicRate(String accountId, String apiKey, String from, String to, String date, String time, Double amount, Boolean obsolete) throws XecdApiException
	{
		return historicRate(accountId, apiKey, from, to, date, time, amount, obsolete, null);
	}

	@Override
	public HistoricRateResponse historicRate(String from, String to, String date, String time, Double amount, Boolean obsolete) throws XecdApiException
	{
		return historicRate(from, to, date, time, amount, obsolete, null);
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String accountId, String apiKey, String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		HistoricRatePeriodResponse response = null;

		String historicRatePeriodString = MessageFormat.format(historicRatePeriodUrl, serverPrefix, to);

		historicRatePeriodString += (from != null && !from.isEmpty()) ? "&from=" + from : "";
		historicRatePeriodString += (amount != null && amount != 0) ? "&amount=" + amount : "";
		historicRatePeriodString += (start != null && !end.isEmpty()) ? "&start_timestamp=" + start : "";
		historicRatePeriodString += (end != null && !end.isEmpty()) ? "&end_timestamp=" + end : "";
		historicRatePeriodString += (interval != null && !interval.isEmpty()) ? "&interval=" + interval : "";
		historicRatePeriodString += (page != null && page != 0) ? "&page=" + page : "";
		historicRatePeriodString += (perPage != null && perPage != 0) ? "&per_page=" + perPage : "";
		historicRatePeriodString += (obsolete != null) ? "&obsolete=" + obsolete.toString() : "";
		historicRatePeriodString += (inverse != null) ? "&inverse=" + inverse.toString() : "";

		try
		{
			logger.debug("Calling {}", historicRatePeriodString);
			String responseString = wsClient.getResponse(historicRatePeriodString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, HistoricRatePeriodResponse.class);
		}
		catch(XecdApiException e)
		{
			logger.error("Error getting historic rates from XECD API", e);
			throw new XecdApiException("Error getting historic rates from XECD API", e, e.getErrorResponse(), e.getHttpStatusCode());
		}
		catch(Exception e)
		{
			logger.error("Error getting historic rates from XECD API", e);
			throw new XecdApiException("Error getting historic rates from XECD API", e);
		}

		return response;
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		return historicRatePeriod(accountId, apiKey, from, to, amount, start, end, interval, page, perPage, obsolete, inverse);
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String accountId, String apiKey, String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete) throws XecdApiException
	{
		return historicRatePeriod(accountId, apiKey, from, to, amount, start, end, interval, page, perPage, obsolete, null);
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete) throws XecdApiException
	{
		return historicRatePeriod(from, to, amount, start, end, interval, page, perPage, obsolete, null);
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String accountId, String apiKey, String from, String to, Double amount, Integer year, Integer month, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		MonthlyAverageResponse response = null;

		String monthlyAverageString = MessageFormat.format(monthlyAverageUrl, serverPrefix, to);
		monthlyAverageString += (from != null && !from.isEmpty()) ? "&from=" + from : "";
		monthlyAverageString += (amount != null && amount != 0) ? "&amount=" + amount : "";
		monthlyAverageString += (year != null) ? "&year=" + year : "";
		monthlyAverageString += (month != null) ? "&month=" + month : "";
		monthlyAverageString += (obsolete != null) ? "&obsolete=" + obsolete.toString() : "";
		monthlyAverageString += (inverse != null) ? "&inverse=" + inverse.toString() : "";

		try
		{
			logger.debug("Calling {}", monthlyAverageString);
			String responseString = wsClient.getResponse(monthlyAverageString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, MonthlyAverageResponse.class);
		}
		catch(XecdApiException e)
		{
			logger.error("Error getting currencies from XECD API", e);
			throw new XecdApiException("Error getting monthly average from XECD API", e, e.getErrorResponse(), e.getHttpStatusCode());
		}
		catch(Exception e)
		{
			logger.error("Error getting currencies from XECD API", e);
			throw new XecdApiException("Error getting monthly average from XECD API", e);
		}

		return response;
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String from, String to, Double amount, Integer year, Integer month, Boolean obsolete, Boolean inverse) throws XecdApiException
	{
		return monthlyAverage(accountId, apiKey, from, to, amount, year, month, obsolete, inverse);
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String from, String to, Double amount, Integer year, Integer month, Boolean obsolete) throws XecdApiException
	{
		return monthlyAverage(from, to, amount, year, month, obsolete, null);
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String accountId, String apiKey, String from, String to, Double amount, Integer year, Integer month, Boolean obsolete) throws XecdApiException
	{
		return monthlyAverage(accountId, apiKey, from, to, amount, year, month, obsolete, null);
	}

}
