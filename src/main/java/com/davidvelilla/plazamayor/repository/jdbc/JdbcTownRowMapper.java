package com.davidvelilla.plazamayor.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.davidvelilla.plazamayor.repository.jdbc.JdbcTown;
import org.springframework.jdbc.core.RowMapper;

class JdbcTownRowMapper implements RowMapper<JdbcTown> {

    @Override
    public JdbcTown mapRow(ResultSet rs, int rownum) throws SQLException {
        JdbcTown town = new JdbcTown();
        town.setId(rs.getInt("towns.id"));
        town.setName(rs.getString("name"));
        town.setLat(rs.getFloat("lat"));
        town.setLon(rs.getFloat("lon"));
        town.setWikiUrl(rs.getString("wiki_url"));
        town.setRegionId(rs.getInt("region_id"));
        return town;
    }
}
