package com.davidvelilla.plazamayor.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.davidvelilla.plazamayor.model.Town;
import com.davidvelilla.plazamayor.repository.TownRepository;
import com.davidvelilla.plazamayor.exception.TownNameTooShortException;

@Service
public class TownServiceImpl implements TownService
{

    private TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository)
    {
        this.townRepository = townRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Town> findTownsByName(String name) throws DataAccessException, TownNameTooShortException
    {
        if (name.length() < 3) {
            throw new TownNameTooShortException("Town name too short");
        }
        return townRepository.findByName("%" + name + "%");
    }

    @Override
    @Transactional(readOnly = true)
    public Town findTownById(int id) throws DataAccessException
    {
        return townRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveTown(Town town) throws DataAccessException
    {
        townRepository.save(town);
    }
}
