package plazamayor;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/fake-data")
public class FakeDataController 
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createFakeTownsData() throws Exception
    {
        FakeDataService.injectDependencies(jdbcTemplate);

        if (counter.incrementAndGet() > 1) {
            return ResponseEntity.status(400).body(Utils.errorStringToJson("Test data already created"));
        }

        List<String> townNames = FakeDataService.getInstance().createFakeTownsData();
        
        return ResponseEntity.ok().body(townNames);
    }
}
