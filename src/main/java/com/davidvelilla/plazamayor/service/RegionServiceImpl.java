package com.davidvelilla.plazamayor.service;

import com.davidvelilla.plazamayor.model.Region;
import com.davidvelilla.plazamayor.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionServiceImpl implements RegionService
{
    private RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository)
    {
        this.regionRepository = regionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Region findRegionById(int id) throws DataAccessException
    {
        return regionRepository.findById(id);
    }
}
