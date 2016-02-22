package com.davidvelilla.plazamayor.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import com.davidvelilla.plazamayor.model.Town;
import com.davidvelilla.plazamayor.repository.TownRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcTownRepositoryImpl implements TownRepository
{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertTown;

    @Autowired
    public JdbcTownRepositoryImpl(DataSource dataSource)
    {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertTown = new SimpleJdbcInsert(dataSource)
            .withTableName("towns")
            .usingGeneratedKeyColumns("id");
    }

    public Collection<Town> findByName(String name) throws DataAccessException
    {
        List<Town> towns = new ArrayList<>();
        // Retrieve the list of all towns matching the name
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        towns.addAll(this.namedParameterJdbcTemplate.query(
            "SELECT * FROM towns WHERE name LIKE :name ORDER BY name",
            params,
            BeanPropertyRowMapper.newInstance(Town.class)));
        return towns;
    }

    @Override
    public Town findById(int id) throws DataAccessException
    {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return this.namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM towns WHERE id=:id",
                params,
                new JdbcTownRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Town.class, id);
        }
    }

    @Override
    public void save(Town town) throws DataAccessException
    {
        if (town.isNew()) {
            Number newKey = this.insertTown.executeAndReturnKey(
                createTownParameterSource(town));
            town.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                "UPDATE towns SET name=:name, lat=:lat, lon=:lon WHERE id=:id",
                createTownParameterSource(town));
        }
    }

    private MapSqlParameterSource createTownParameterSource(Town town)
    {
        return new MapSqlParameterSource()
            .addValue("id", town.getId())
            .addValue("name", town.getName())
            .addValue("lat", town.getLat())
            .addValue("lon", town.getLon());
    }
}
