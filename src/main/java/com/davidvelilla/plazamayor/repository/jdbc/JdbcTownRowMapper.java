package com.davidvelilla.plazamayor.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.davidvelilla.plazamayor.model.Town;
import org.springframework.jdbc.core.RowMapper;

class JdbcTownRowMapper implements RowMapper<Town> {

    @Override
    public Town mapRow(ResultSet rs, int rownum) throws SQLException {
        Town town = new Town();
        town.setId(rs.getInt("towns.id"));
        town.setName(rs.getString("name"));
        town.setLat(rs.getFloat("lat"));
        town.setLon(rs.getFloat("lon"));
        town.setWikiUrl(rs.getString("wiki_url"));
        town.setRegionId(rs.getInt("region_id"));
        return town;
    }
}
