package com.xe.xecdApiClient.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.xe.xecdApiClient.exception.XecdApiException;
import org.apache.http.client.ClientProtocolException;

public interface XecdHttpClient
{
	String getResponse(String url, String username, String password) throws URISyntaxException, ClientProtocolException, IOException, XecdApiException;
}
