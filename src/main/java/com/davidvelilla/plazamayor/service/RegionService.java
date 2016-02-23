package com.davidvelilla.plazamayor.service;

import com.davidvelilla.plazamayor.model.Region;
import org.springframework.dao.DataAccessException;

public interface RegionService
{
    Region findRegionById(int id) throws DataAccessException;
}
