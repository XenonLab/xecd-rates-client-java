package com.xe.xecdApiClient.config;

import com.google.gson.Gson;

public class XecdApiConfigBean
{

	private String accountId;
	private String apiKey;
	private String serverPrefix;
	private Integer connectTimeout;
	private Boolean useSystemProperties;

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getApiKey()
	{
		return apiKey;
	}

	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}

	public String getServerPrefix()
	{
		return serverPrefix;
	}

	public void setServerPrefix(String serverPrefix)
	{
		this.serverPrefix = serverPrefix;
	}

	@Override
	public String toString()
	{
		return new Gson().toJson(this);
	}

	public Integer getConnectTimeout()
	{
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout)
	{
		this.connectTimeout = connectTimeout;
	}

	public Boolean useSystemProperties() {
		return useSystemProperties;
	}

	public void setUseSystemProperties(final Boolean useSystemProperties) {
		this.useSystemProperties = useSystemProperties;
	}
}
