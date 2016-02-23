package com.davidvelilla.plazamayor.repository.springdatajpa;

import com.davidvelilla.plazamayor.model.Town;
import com.davidvelilla.plazamayor.repository.TownRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface SpringDataTownRepository extends TownRepository, Repository<Town, Integer>
{
    @Override
    @Query("SELECT DISTINCT town FROM Town town left join fetch town.region WHERE town.name LIKE :name")
    Collection<Town> findByName(@Param("name") String name);

    @Override
    @Query("SELECT town FROM Town town left join fetch town.region WHERE town.id =:id")
    Town findById(@Param("id") int id);
}
