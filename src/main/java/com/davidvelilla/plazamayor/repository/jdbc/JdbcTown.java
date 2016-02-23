package com.davidvelilla.plazamayor.repository.jdbc;

import com.davidvelilla.plazamayor.model.Town;

class JdbcTown extends Town
{
    private int regionId;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}
