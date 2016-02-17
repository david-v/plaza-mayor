package plazamayor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application 
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) 
    {
        SpringApplication.run(Application.class, args);
    }

    public static Logger getLogger()
    {
    	return log;
    }

    public static String likeSanitize(String input) 
    {
	    return input
	       .replace("!", "!!")
	       .replace("%", "!%")
	       .replace("_", "!_")
	       .replace("[", "![");
	} 

	public static String errorMessage(String message) 
    {
	    return "{\"error\": \"" + message + "\"}";
	}
}