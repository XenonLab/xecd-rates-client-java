package com.xe.xecdApiClient.exception;

import com.xe.xecdApiClient.exception.ErrorResponse;

public class XecdApiException extends Exception
{
	/**
	 *
	 */
	private Integer httpStatusCode;
	private ErrorResponse errorResponse;

	private static final long serialVersionUID = -4024493361826460130L;

	public XecdApiException(Throwable e)
	{
		super(e);
	}

	public XecdApiException(String message)
	{
		super(message);
	}

	public XecdApiException(String message, ErrorResponse errorResponse, Integer httpStatusCode)
	{
		super(message);
		this.errorResponse = errorResponse;
		this.httpStatusCode = httpStatusCode;
	}

	public XecdApiException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public XecdApiException(String message, Throwable cause, ErrorResponse errorResponse, Integer httpStatusCode)
	{
		super(message, cause);
		this.errorResponse = errorResponse;
		this.httpStatusCode = httpStatusCode;
	}

	public ErrorResponse getErrorResponse()
	{
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse)
	{
		this.errorResponse = errorResponse;
	}

	public Integer getHttpStatusCode()
	{
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode)
	{
		this.httpStatusCode = httpStatusCode;
	}
}

