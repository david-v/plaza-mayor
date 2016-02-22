package com.davidvelilla.plazamayor.repository.springdatajpa;

import com.davidvelilla.plazamayor.model.Town;
import com.davidvelilla.plazamayor.repository.TownRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataTownRepository extends TownRepository, Repository<Town, Integer>
{

}
