package plazamayor;

import org.slf4j.Logger;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class TownService
{
    private JdbcTemplate db;
    private Logger log;
    private static TownService instance = null;

    private TownService(JdbcTemplate db)
    {
        //Singleton
        this.db = db;
        this.log = Application.getLogger();
    }

    public static void injectDependencies(JdbcTemplate db)
    {
        if (instance == null) {
            instance = new TownService(db);
        }
    }

    public static TownService getInstance() throws BaseException
    {
        if (instance == null) {
            throw new BaseException("Instance of TownService not initialised yet");
        }
        return instance;
    }

    public List<Town> getTown(String townId) throws TownNameTooShortException
    {
        String queryString = "SELECT id, name, lat, lon FROM towns WHERE";
        Object[] queryParams;

        try {
            int numericId = Integer.parseInt(townId);
            queryString += " id = ?";
            queryParams = new Object[] {numericId};
        } catch (NumberFormatException e) {
            // not an integer!
            if (townId.length() < 3) {
                throw new TownNameTooShortException("Town name too short");
            }
            queryString += " name LIKE ? ESCAPE '!'";
            queryParams = new Object[] {"%" + Utils.likeSanitize(townId) + "%" };
        }

        this.log.info("Searching Town '" + townId + "'");

        List<Town> queryResult = this.db.query(
            queryString,
            queryParams,
            (rs, rowNum) -> new Town(
                rs.getLong("id"),
                rs.getString("name"), 
                rs.getFloat("lat"),
                rs.getFloat("lon")
            )
        );
        return queryResult;
    }
}
