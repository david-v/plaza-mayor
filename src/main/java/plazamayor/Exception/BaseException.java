package plazamayor;

import java.lang.Exception;

class BaseException extends Exception
{
	protected static int statusCode = 500;

	public BaseException(String message)
	{
		super(message);
		Application.getLogger().error(Integer.toString(statusCode) + ": " + message);
	}

	public int getStatusCode()
	{
		return statusCode;
	}
}