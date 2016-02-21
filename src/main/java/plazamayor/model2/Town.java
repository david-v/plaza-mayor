package plazamayor.model;

public class Town 
{
    private long id;
    private String name; 
    private float lat;
    private float lon;

    public Town() 
    {
        
    }

    public Town(long id, String name, float lat, float lon) 
    {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return String.format(
            "Town[id=%d, name='%s']",
            this.id, this.name
        );
    }

    public long getId()
    {
        return this.id;
    }

    public Town setId(long id)
    {
        this.id = id;
        return this;
    }

    public String getName()
    {
        return this.name;
    }

    public Town setName(String name)
    {
        this.name = name;
        return this;
    }

    public float getLat()
    {
        return this.lat;
    }

    public Town setLat(float lat)
    {
        this.lat = lat;
        return this;
    }

    public float getLon()
    {
        return this.lon;
    }

    public Town setLon(float lon)
    {
        this.lon = lon;
        return this;
    }
}
