package com.davidvelilla.plazamayor.repository;

import com.davidvelilla.plazamayor.model.Region;
import org.springframework.dao.DataAccessException;

public interface RegionRepository
{
    /**
     * Retrieve an <code>Region</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Region</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Region findById(int id) throws DataAccessException;
}
