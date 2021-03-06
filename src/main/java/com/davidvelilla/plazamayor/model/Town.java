package com.davidvelilla.plazamayor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends NamedEntity
{
    @Column(name = "lat")
    private Float lat;

    @Column(name = "lon")
    private Float lon;

    @Column(name = "wiki_url")
    private String wikiUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public Float getLat() {
        return this.lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return this.lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getRegionName()
    {
        return this.region.getName();
    }
}
