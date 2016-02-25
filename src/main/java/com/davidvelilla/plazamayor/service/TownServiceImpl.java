package com.davidvelilla.plazamayor.service;

import com.davidvelilla.plazamayor.exception.TownNameTooShortException;
import com.davidvelilla.plazamayor.model.Town;
import com.davidvelilla.plazamayor.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        String joined;
        if (name.contains(" ")) {
            List<String> ignoredWords = Arrays.asList("de", "el", "del", "la", "los", "las");

            String[] split = name.split(" ");
            StringBuilder sb = new StringBuilder();
            sb.append("%");
            for (String word : split) {
                if (ignoredWords.contains(word)) {
                    continue;
                }
                sb.append(word);
                sb.append("%");
            }
            joined = sb.toString();
        } else {
            joined = "%" + name + "%";
        }
        if (joined.length() < 3) {
            throw new TownNameTooShortException("Town name too short");
        }
        return townRepository.findByName(joined);
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
