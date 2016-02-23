package com.davidvelilla.plazamayor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "regions")
public class Region extends NamedEntity
{
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private Set<Town> towns;

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    public Map<Integer, String> getTownsList() {
        Map<Integer, String> dictionary = new HashMap<>();
        for (Town town : this.towns) {
            dictionary.put(town.getId(), town.getName());
        }
        return dictionary;
    }
}
