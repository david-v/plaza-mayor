package com.davidvelilla.plazamayor.exception;

import java.lang.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseException extends Exception
{
	protected static int statusCode = 500;

	public BaseException(String message)
	{
		super(message);
        Logger logger = LoggerFactory.getLogger(BaseException.class);
		logger.error(Integer.toString(statusCode) + ": " + message);
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
