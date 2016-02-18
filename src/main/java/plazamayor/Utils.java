package plazamayor;

public class Utils 
{
    public static String likeSanitize(String input) 
    {
	    return input
	       .replace("!", "!!")
	       .replace("%", "!%")
	       .replace("_", "!_")
	       .replace("[", "![");
	}

    public static String errorStringToJson(String message)
    {
        return "{\"error\": \"" + message + "\"}";
    }
}