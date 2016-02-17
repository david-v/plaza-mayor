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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fake-data")
public class FakeDataController 
{
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createFakePueblosData() throws Exception
    {
        if (counter.incrementAndGet() > 1) {
            return ResponseEntity.badRequest().body(Application.errorMessage("Datos de prueba ya existen"));
        }

        Logger log = Application.getLogger();

        log.info("Creando pueblos");

        jdbcTemplate.execute("DROP TABLE pueblos IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE pueblos(id SERIAL, nombre VARCHAR(255), lat FLOAT, lon FLOAT)");

        List<String> pueblosNombres = Arrays.asList("Segura de Baños", "Salcedillo", "Maicas", "Vivel del Rio", "Martin del Rio");
        List<Object[]> pueblos = pueblosNombres.stream().map( name -> new String[] {name} ).collect(Collectors.toList());

        pueblos.forEach(pueblo -> log.info(String.format("Insertando pueblo '%s'", pueblo[0])));

        jdbcTemplate.batchUpdate("INSERT INTO pueblos(nombre) VALUES (?)", pueblos);

        return ResponseEntity.ok().body(pueblosNombres);
    }
}
