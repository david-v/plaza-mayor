package com.davidvelilla.plazamayor.repository;

import java.util.Collection;

import com.davidvelilla.plazamayor.model.Town;
import org.springframework.dao.DataAccessException;
import com.davidvelilla.plazamayor.model.BaseEntity;

public interface TownRepository
{
    /**
     * Retrieve <code>Town</code>s from the data store by name, returning all that match
     *
     * @param name Value to search for
     * @return a <code>Collection</code> of matching <code>Owner</code>s
     */
    Collection<Town> findByName(String name) throws DataAccessException;

    /**
     * Retrieve an <code>Town</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Town</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Town findById(int id) throws DataAccessException;


    /**
     * Save an <code>Town</code> to the data store, either inserting or updating it.
     *
     * @param town the <code>Town</code> to save
     * @see BaseEntity#isNew
     */
    void save(Town town) throws DataAccessException;
}
