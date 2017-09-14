package com.xe.xecdApiClient.exception;

public class ErrorResponse
{
	private int code;
	private String message;
	private String documentation_url;

	public ErrorResponse(int code, String message, String documentation_url)
	{
		this.code = code;
		this.message = message;
		this.documentation_url = documentation_url;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getDocumentation_url()
	{
		return documentation_url;
	}

	public void setDocumentation_url(String documentation_url)
	{
		this.documentation_url = documentation_url;
	}
}
