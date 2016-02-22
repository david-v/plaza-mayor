package com.davidvelilla.plazamayor.service;

import java.util.Collection;

import com.davidvelilla.plazamayor.model.Town;
import org.springframework.dao.DataAccessException;
import com.davidvelilla.plazamayor.exception.TownNameTooShortException;

public interface TownService
{

    Collection<Town> findTownsByName(String name) throws DataAccessException, TownNameTooShortException;

    Town findTownById(int id) throws DataAccessException;

    void saveTown(Town town) throws DataAccessException;
}
