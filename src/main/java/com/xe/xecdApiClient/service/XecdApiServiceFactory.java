package com.xe.xecdApiClient.service;

import com.xe.xecdApiClient.config.XecdApiConfigBean;
import com.xe.xecdApiClient.exception.XecdApiException;

public class XecdApiServiceFactory
{
	
	/**
	 * Creates a custom service which uses a configuration bean created by the api user
	 * @param config
	 * @return
	 */
	public static XecdApiService createXecdAPIService(XecdApiConfigBean config)
	{
		
		return new XecdApiServiceImpl(config);
	}

	/**
	 * Creates an XecdAPIService using environment variables:
	 * - XECD_API_KEY
	 * - XECD_ACCOUNT_ID
	 * for required environment variables
	 * @return -- new XecdAPIService if all variables are present
	 * @return -- null if not all variables are present
	 * @throws XecdApiException 
	 */
	public static XecdApiService createXecdAPIService() throws XecdApiException
	{
		return new XecdApiServiceImpl();
	}
}
