package com.davidvelilla.plazamayor.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.davidvelilla.plazamayor.model.Town;
import com.davidvelilla.plazamayor.repository.TownRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTownRepositoryImpl implements TownRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Town town) {
        if (town.getId() == null) {
            this.em.persist(town);
        } else {
            this.em.merge(town);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Town findById(int id) {
        return this.em.find(Town.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Town> findByName(String name) {
        return this.em.createQuery("SELECT * FROM towns WHERE name LIKE :name ORDER BY name")
            .setParameter("name", "%" + name + "%")
            .getResultList();
    }
}
