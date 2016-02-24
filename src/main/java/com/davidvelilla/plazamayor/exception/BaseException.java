package com.davidvelilla.plazamayor.exception;

import java.lang.Exception;

public class BaseException extends Exception
{
	protected static int statusCode = 500;

	public BaseException(String message)
	{
		super(message);
	}

	public int getStatusCode()
	{
		return statusCode;
	}

    public String getJsonMessage()
    {
        return "{\"error\": \"" + this.getMessage() + "\"}";
    }
}
