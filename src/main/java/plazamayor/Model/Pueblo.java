package plazamayor;

public class Pueblo 
{
    private long id;
    private String nombre; 
    private float lat;
    private float lon;

    public Pueblo() 
    {
        
    }

    public Pueblo(long id, String nombre, float lat, float lon) 
    {
        this.id = id;
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return String.format(
            "Pueblo[id=%d, nombre='%s']",
            this.id, this.nombre
        );
    }

    public long getId()
    {
        return this.id;
    }

    public Pueblo setId(long id)
    {
        this.id = id;
        return this;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public Pueblo setNombre(String nombre)
    {
        this.nombre = nombre;
        return this;
    }

    public float getLat()
    {
        return this.lat;
    }

    public Pueblo setLat(float lat)
    {
        this.lat = lat;
        return this;
    }

    public float getLon()
    {
        return this.lon;
    }

    public Pueblo setLon(float lon)
    {
        this.lon = lon;
        return this;
    }
}
