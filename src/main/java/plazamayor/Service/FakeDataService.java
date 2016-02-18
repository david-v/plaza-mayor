package plazamayor;

import org.slf4j.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class FakeDataService
{
    public static List<String> createFakeTownsData(JdbcTemplate db) throws Exception
    {
        Logger log = Application.getLogger();

        log.info("Creating towns...");

        db.execute("DROP TABLE IF EXISTS towns");
        db.execute("CREATE TABLE towns(id SERIAL, name VARCHAR(255), lat FLOAT, lon FLOAT)");

        List<String> townNames = Arrays.asList("Segura de Baños", "Salcedillo", "Maicas", "Vivel del Rio", "Martin del Rio");
        List<Object[]> towns = townNames.stream().map( name -> new String[] {name} ).collect(Collectors.toList());

        towns.forEach(pueblo -> log.info(String.format("Inserting town '%s'", pueblo[0])));

        db.batchUpdate("INSERT INTO towns(name) VALUES (?)", towns);

        return townNames;
    }
}
