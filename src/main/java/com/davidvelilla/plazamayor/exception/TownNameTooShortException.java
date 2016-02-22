package com.davidvelilla.plazamayor.exception;

public class TownNameTooShortException extends BaseException
{
	static {
		statusCode = 400;
	}

	public TownNameTooShortException(String message)
	{
		super(message);
	}
}
