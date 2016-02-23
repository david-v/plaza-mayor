package com.davidvelilla.plazamayor.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.davidvelilla.plazamayor.model.Region;
import org.springframework.jdbc.core.RowMapper;

class JdbcRegionRowMapper implements RowMapper<Region> {

    @Override
    public Region mapRow(ResultSet rs, int rownum) throws SQLException {
        Region region = new Region();
        region.setId(rs.getInt("regions.id"));
        region.setName(rs.getString("name"));
        return region;
    }
}
