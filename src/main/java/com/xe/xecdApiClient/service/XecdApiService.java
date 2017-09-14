package com.xe.xecdApiClient.service;

import com.xe.xecdApiClient.exception.XecdApiException;
import com.xe.xecdApiClient.model.*;

public interface XecdApiService
{
	/**
	 * Returns basic information about the XECD account being used to send the request
	 * 
	 * @param accountId Your API account ID
	 * @param apiKey Your API key
	 * @return 	AccountInfoResponse object containing account information for the XECD API account used
	 */
	AccountInfoResponse getAccountInfo(String accountId, String apiKey) throws XecdApiException;
	
	/**
	 * Returns basic information about the XECD account being used to send the request
	 * 
	 * @return 	AccountInfoResponse object containing account information for the XECD API account used
	 */
	AccountInfoResponse getAccountInfo() throws XecdApiException;
	
	/**
	 * Fetches a list of all currencies, active and obsolete
	 * 
	 * @param accountId Your API account ID
	 * @param apiKey Your API key
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param language The language to return the currency codes in.
	 * 					Languages include: "ar", "de", "en", "es", "fr", "it", "ja", "pt", "sv", "zh-CN", and "zh-HK"
	 * @param iso Comma-separated list of ISO 4217 codes that specifies currencies provided
	 * @return CurrenciesResponse containing the list of all currencies
	 */
	CurrenciesResponse getCurrencies(String accountId, String apiKey, Boolean obsolete, String language, String iso) throws XecdApiException;
	CurrenciesResponse getCurrencies(String accountId, String apiKey, Boolean obsolete, String language) throws XecdApiException;

	/**
	 * Fetches a list of all currencies, active and obsolete
	 * 
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param language The language to return the currency codes in.
	 * 					Languages include: "ar", "de", "en", "es", "fr", "it", "ja", "pt", "sv", "zh-CN", and "zh-HK"
	 * @param iso Comma-separated list of ISO 4217 codes that specifies currencies provided
	 * @return CurrenciesResponse containing the list of all currencies
	 */
	CurrenciesResponse getCurrencies(Boolean obsolete, String language, String iso) throws XecdApiException;
	CurrenciesResponse getCurrencies(Boolean obsolete, String language) throws XecdApiException;

	/**
	 * Converts from a currency amount to multiple other currencies
	 * 
	 * @param accountId Your API account ID
	 * @param apiKey Your API key
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param amount The amount you want to convert
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return ConvertFromResponse object containing the converted currencies
	 */
	ConvertFromResponse convertFrom(String accountId, String apiKey, String from, String to, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException;
	ConvertFromResponse convertFrom(String accountId, String apiKey, String from, String to, Double amount, Boolean obsolete) throws XecdApiException;

	/**
	 * Converts from a currency amount to multiple other currencies
	 * 
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param amount The amount you want to convert
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return ConvertFromResponse object containing the converted currencies
	 */
	ConvertFromResponse convertFrom(String from, String to, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException;
	ConvertFromResponse convertFrom(String from, String to, Double amount, Boolean obsolete) throws XecdApiException;

	/**
	 * Converts to a currency amount from multiple other currencies
	 * 
	 * @param accountId Your API account ID
	 * @param apiKey Your API key
	 * @param to The currency you want to convert to
	 * @param from Comma separated list of from currencies. Use * to convert all currencies.
	 * @param amount The amount you want to convert
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return ConvertToResponse object containing the converted currencies
	 */
	ConvertToResponse convertTo(String accountId, String apiKey, String to, String from, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException;
	ConvertToResponse convertTo(String accountId, String apiKey, String to, String from, Double amount, Boolean obsolete) throws XecdApiException;

	/**
	 * Converts to a currency amount from multiple other currencies
	 * 
	 * @param to The currency you want to convert to
	 * @param from Comma separated list of from currencies. Use * to convert all currencies.
	 * @param amount The amount you want to convert
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return ConvertToResponse object containing the converted currencies
	 */
	ConvertToResponse convertTo(String to, String from, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException;
	ConvertToResponse convertTo(String to, String from, Double amount, Boolean obsolete) throws XecdApiException;

	/**
	 * Returns the historic rate for a single base currency and one or more counter currencies
	 * 
	 * @param accountId Your API account ID
	 * @param apiKey Your API key
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param date UTC date representing the historic date you want to convert to in format of YYYY-MM-DD
	 * @param time Specific time of day in UTC format of HH:MM
	 * @param amount The amount you want to convert
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return HistoricRateResponse object containing the historic rate data
	 */
	HistoricRateResponse historicRate(String accountId, String apiKey, String from, String to, String date, String time, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException;
	HistoricRateResponse historicRate(String accountId, String apiKey, String from, String to, String date, String time, Double amount, Boolean obsolete) throws XecdApiException;

	/**
	 * Returns the historic rate for a single base currency and one or more counter currencies
	 * 
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param date UTC date representing the historic date you want to convert to in format of YYYY-MM-DD
	 * @param time Specific time of day in UTC format of HH:MM
	 * @param amount The amount you want to convert
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return HistoricRateResponse object containing the historic rate data
	 */
	HistoricRateResponse historicRate(String from, String to, String date, String time, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException;
	HistoricRateResponse historicRate(String from, String to, String date, String time, Double amount, Boolean obsolete) throws XecdApiException;

	/**
	 * Returns a daily historic rate for a single base currency and one or more counter currencies over a period of time
	 * 
	 * @param accountId Your API account ID
	 * @param apiKey Your API key
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param amount The amount you want to convert
	 * @param start The starting timestamp in the UTC format yyyy-mm-ddThh:mm
	 * @param end The ending timestamp in the UTC format yyyy-mm-ddThh:mm
	 * @param interval Specifies daily or hourly data, interval is daily by default
	 * @param page The page number to request. The default is the first page (page 1)
	 * @param perPage The number of results per page. Default is 30 per page up to a maximum of 100 per page
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return HistoricRatePeriodResponse object containing the daily historical rates data
	 */
	HistoricRatePeriodResponse historicRatePeriod(String accountId, String apiKey, String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete, Boolean inverse) throws XecdApiException;
	HistoricRatePeriodResponse historicRatePeriod(String accountId, String apiKey, String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete) throws XecdApiException;

	/**
	 * Returns a daily historic rate for a single base currency and one or more counter currencies over a period of time
	 * 
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param amount The amount you want to convert
	 * @param start The starting timestamp in the UTC format yyyy-mm-ddThh:mm
	 * @param end The ending timestamp in the UTC format yyyy-mm-ddThh:mm
	 * @param interval Specifies daily or hourly data, interval is daily by default
	 * @param page The page number to request. The default is the first page (page 1)
	 * @param perPage The number of results per page. Default is 30 per page up to a maximum of 100 per page
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 * @return HistoricRatePeriodResponse object containing the daily historical rates data
	 */
	HistoricRatePeriodResponse historicRatePeriod(String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete, Boolean inverse) throws XecdApiException;
	HistoricRatePeriodResponse historicRatePeriod(String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete) throws XecdApiException;

	/**
	 * Returns monthly average rates for a single base currency and one or more counter currencies for a year and optionally month
	 *
	 * @param accountId Your API account ID
	 * @param apiKey Your API key
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param amount The amount you want to convert
	 * @param year The specified year to calculate average monthly rates
	 * @param month The specified month in given year to return average monthly rates
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 */
	MonthlyAverageResponse monthlyAverage(String accountId, String apiKey, String from, String to, Double amount, Integer year, Integer month, Boolean obsolete, Boolean inverse) throws XecdApiException;
	MonthlyAverageResponse monthlyAverage(String accountId, String apiKey, String from, String to, Double amount, Integer year, Integer month, Boolean obsolete) throws XecdApiException;

	/**
	 * Returns monthly average rates for a single base currency and one or more counter currencies for a year and optionally month
	 *
	 * @param from The currency you want to convert from
	 * @param to Comma separated list of to currencies. Use * to convert to all currencies.
	 * @param amount The amount you want to convert
	 * @param year The specified year to calculate average monthly rates
	 * @param month The specified month in given year to return average monthly rates
	 * @param obsolete Specifies whether to return obsolete currencies
	 * @param inverse Specifies whether to return inverse rates
	 */
	MonthlyAverageResponse monthlyAverage(String from, String to, Double amount, Integer year, Integer month, Boolean obsolete, Boolean inverse) throws XecdApiException;
	MonthlyAverageResponse monthlyAverage(String from, String to, Double amount, Integer year, Integer month, Boolean obsolete) throws XecdApiException;


}
