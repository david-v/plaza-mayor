package com.davidvelilla.plazamayor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends NamedEntity
{
    @Column(name = "lat")
    private float lat;

    @Column(name = "lon")
    private float lon;

    public float getLat()
    {
        return this.lat;
    }

    public void setLat(float lat)
    {
        this.lat = lat;
    }

    public float getLon()
    {
        return this.lon;
    }

    public void setLon(float lon)
    {
        this.lon = lon;
    }
}
