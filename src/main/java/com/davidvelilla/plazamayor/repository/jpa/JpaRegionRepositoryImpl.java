package com.davidvelilla.plazamayor.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.davidvelilla.plazamayor.model.Region;
import com.davidvelilla.plazamayor.repository.RegionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRegionRepositoryImpl implements RegionRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public Region findById(int id) {
        return this.em.find(Region.class, id);
    }
}
