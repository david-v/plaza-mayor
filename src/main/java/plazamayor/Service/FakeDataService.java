package plazamayor;

import org.slf4j.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class FakeDataService
{
    private JdbcTemplate db;
    private Logger log;
    private static FakeDataService instance = null;

    private FakeDataService(JdbcTemplate db)
    {
        //Singleton
        this.db = db;
        this.log = Application.getLogger();
    }

    public static void injectDependencies(JdbcTemplate db)
    {
        if (instance == null) {
            instance = new FakeDataService(db);
        }
    }

    public static FakeDataService getInstance() throws BaseException
    {
        if (instance == null) {
            throw new BaseException("Instance of FakeDataService not initialised yet");
        }
        return instance;
    }

    public List<String> createFakeTownsData() throws Exception
    {
        this.log.info("Creating towns...");

        this.db.execute("DROP TABLE IF EXISTS towns");
        this.db.execute("CREATE TABLE towns(id SERIAL, name VARCHAR(255), lat FLOAT, lon FLOAT)");

        List<String> townNames = Arrays.asList("Segura de Baños", "Salcedillo", "Maicas", "Vivel del Rio", "Martin del Rio");
        List<Object[]> towns = townNames.stream().map( name -> new String[] {name} ).collect(Collectors.toList());

        towns.forEach(pueblo -> this.log.info(String.format("Inserting town '%s'", pueblo[0])));

        this.db.batchUpdate("INSERT INTO towns(name) VALUES (?)", towns);

        return townNames;
    }
}
