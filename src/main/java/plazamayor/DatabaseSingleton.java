package plazamayor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseSingleton 
{
    private static DatabaseSingleton instance = null;

    protected JdbcTemplate jdbcTemplate;

    protected DatabaseSingleton()
    {
        // Singleton
    }

    public static DatabaseSingleton getInstance()
    {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public JdbcTemplate getDB()
    {
        return this.jdbcTemplate;
    }

    public void setDB(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
}