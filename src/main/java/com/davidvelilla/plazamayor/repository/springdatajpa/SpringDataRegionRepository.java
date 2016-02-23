package com.davidvelilla.plazamayor.repository.springdatajpa;

import com.davidvelilla.plazamayor.model.Region;
import com.davidvelilla.plazamayor.repository.RegionRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataRegionRepository extends RegionRepository, Repository<Region, Integer>
{
    @Override
    @Query("SELECT region FROM Region region left join fetch region.towns WHERE region.id =:id")
    Region findById(@Param("id") int id);
}
