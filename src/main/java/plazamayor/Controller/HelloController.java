package plazamayor;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController 
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/hello/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) 
    {
        return new Greeting(
            counter.incrementAndGet(), 
            String.format(template, name)
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hello/test")
    public String teststuff(String name) 
    {
        Greeting greeting = new Greeting(1, "asdf");
        return "Printing: " + greeting;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hello/consume")
    public Quote consume()
    {
        counter.incrementAndGet();

        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        Application.getLogger().info(quote.toString());
        return quote;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hello/init-db")
    public List<Object[]> initDb() throws Exception
    {
        Logger log = Application.getLogger();

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
        return splitUpNames;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello/customer")
    public List<Customer> getCustomer(@RequestParam(value="name", defaultValue="Josh") String name) throws Exception
    {
        Logger log = Application.getLogger();

        log.info("Querying for customer records where first_name = '" + name + "':");
        List<Customer> queryResult = jdbcTemplate.query(
            "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", 
            new Object[] { name },
            (rs, rowNum) -> new Customer(
                rs.getLong("id"),
                rs.getString("first_name"), 
                rs.getString("last_name")
            )
        );
        queryResult.forEach(customer -> log.info(customer.toString()));
        return queryResult;
    }
}
