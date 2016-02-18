package plazamayor;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/towns")
public class TownController 
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/{townId}", produces = "application/json")
    public ResponseEntity getTown(@PathVariable("townId") String townId)
    {
        try {
            List<Town> towns = TownService.getTown(townId, jdbcTemplate);
            return ResponseEntity.ok().body(towns);
        } catch (TownNameTooShortException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Utils.errorStringToJson(e.getMessage()));
        }
    }

    // @RequestMapping(method = RequestMethod.PUT, value = "/wiki")
    // public Quote consume()
    // {
    //     RestTemplate restTemplate = new RestTemplate();
    //     Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    //     Application.getLogger().info(quote.toString());
    //     return quote;
    // }
}
