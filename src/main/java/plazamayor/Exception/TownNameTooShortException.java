package plazamayor;

class TownNameTooShortException extends BaseException
{
	static {
		statusCode = 400;
	}
	
	public TownNameTooShortException(String message)
	{
		super(message);
	}
}