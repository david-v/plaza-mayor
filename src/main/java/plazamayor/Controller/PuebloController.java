package plazamayor;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pueblos")
public class PuebloController 
{
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/{puebloId}", produces = "application/json")
    public ResponseEntity getPueblo(@PathVariable("puebloId") String puebloId)
    {
        Logger log = Application.getLogger();

        String queryString = "SELECT id, nombre, lat, lon FROM pueblos WHERE";
        Object[] queryParams;

        try {
            int numericId = Integer.parseInt(puebloId);
            queryString += " id = ?";
            queryParams = new Object[] {numericId};
        } catch (NumberFormatException e) {
            // not an integer!
            if (puebloId.length() < 3) {
                return ResponseEntity.badRequest().body(Application.errorMessage("Nombre de pueblo demasiado corto"));
            }
            queryString += " nombre LIKE ? ESCAPE '!'";
            queryParams = new Object[] {"%" + Application.likeSanitize(puebloId) + "%" };
        }

        log.info("Buscando Pueblo '" + puebloId + "'");

        List<Pueblo> queryResult = jdbcTemplate.query(
            queryString,
            queryParams,
            (rs, rowNum) -> new Pueblo(
                rs.getLong("id"),
                rs.getString("nombre"), 
                rs.getFloat("lat"),
                rs.getFloat("lon")
            )
        );
        queryResult.forEach(pueblo -> log.info(pueblo.toString()));
        return ResponseEntity.ok().body(queryResult);
    }
}
