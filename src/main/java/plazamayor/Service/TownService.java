package plazamayor;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class TownService
{
    public static List<Town> getTown(String townId, JdbcTemplate jdbcTemplate) throws TownNameTooShortException
    {
        Logger log = Application.getLogger();

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

        log.info("Searching Town '" + townId + "'");

        List<Town> queryResult = jdbcTemplate.query(
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
