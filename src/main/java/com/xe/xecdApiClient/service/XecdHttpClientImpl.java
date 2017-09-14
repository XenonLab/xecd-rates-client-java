package com.xe.xecdApiClient.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.xe.xecdApiClient.exception.ErrorResponse;
import com.xe.xecdApiClient.exception.XecdApiException;
import com.xe.xecdApiClient.utils.JsonUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import javax.net.ssl.SSLContext;

public class XecdHttpClientImpl implements XecdHttpClient
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private HttpClient client = null;

	public XecdHttpClientImpl(Integer connectTimeout)
	{
		HttpClientBuilder builder = HttpClientBuilder.create();
		if (connectTimeout != null)
		{
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).build();
			builder.setDefaultRequestConfig(requestConfig);
		}
		try
		{
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null,null,null);
			builder.setSSLContext(sslContext);
			client = builder.build();
		}
		catch(Exception e)
		{
			logger.error("Could not establish SSL Context properly for HTTPClient.");
			client = null;
		}
	}

	@Override
	public String getResponse(String url, String username, String password) throws URISyntaxException, ClientProtocolException, IOException, XecdApiException
	{
		JsonUtils jsonUtils = new JsonUtils();
		HttpGet get = new HttpGet(new URI(url));
		Base64 base64 = new Base64();
		String encodedUsernamePassword = base64.encodeAsString((username + ":" + password).getBytes());

		get.setHeader("Authorization", "Basic " + encodedUsernamePassword);

		if(logger.isDebugEnabled())
		{
			logger.debug("request = {}", new Gson().toJson(get));
		}

		HttpResponse response = client.execute(get);

		HttpEntity bodyEntity = response.getEntity();

		String responseString = EntityUtils.toString(bodyEntity);

		logger.debug("response = {}", responseString);

		if (response.getStatusLine().getStatusCode() != 200)
		{
			ErrorResponse errorResponse = jsonUtils.fromJson(responseString, ErrorResponse.class);
			throw new XecdApiException("Error received from XECD with status = " + response.getStatusLine(), errorResponse, response.getStatusLine().getStatusCode());
		}

		return responseString;
	}
}
