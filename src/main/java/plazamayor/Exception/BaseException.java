package plazamayor;

import java.lang.Exception;

class BaseException extends Exception
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
}