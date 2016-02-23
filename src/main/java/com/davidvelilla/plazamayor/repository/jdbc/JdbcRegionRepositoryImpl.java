package com.davidvelilla.plazamayor.repository.jdbc;

import com.davidvelilla.plazamayor.model.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import com.davidvelilla.plazamayor.model.Region;
import com.davidvelilla.plazamayor.repository.RegionRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcRegionRepositoryImpl implements RegionRepository
{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcRegionRepositoryImpl(DataSource dataSource)
    {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Region findById(int id) throws DataAccessException
    {
        Region region;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            region = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM regions WHERE id=:id",
                params,
                new JdbcRegionRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Region.class, id);
        }
        injectTowns(region);
        return region;
    }

    public void injectTowns(Region region) throws DataAccessException
    {
        // Retrieve the list of all towns matching the name
        Map<String, Object> params = new HashMap<>();
        params.put("region_id", region.getId());
        Collection<JdbcTown> results = this.namedParameterJdbcTemplate.query(
            "SELECT * FROM towns WHERE region_id=:region_id",
            params,
            new JdbcTownRowMapper()
        );
        Set<Town> towns = new HashSet<>();
        for (JdbcTown result : results) {
            result.setRegion(region);
            towns.add(result);
        }
        region.setTowns(towns);
    }
}
