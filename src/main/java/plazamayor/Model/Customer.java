package plazamayor;

public class Customer 
{
    private long id;
    private String firstName; 
    private String lastName;

    public Customer(long id, String firstName, String lastName) 
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s']",
            id, firstName, lastName
        );
    }

    public long getId()
    {
        return this.id;
    }

    public Customer setId()
    {
        this.id = id;
        return this;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public Customer setFirstName()
    {
        this.firstName = firstName;
        return this;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public Customer setLastName()
    {
        this.lastName = lastName;
        return this;
    }
}
