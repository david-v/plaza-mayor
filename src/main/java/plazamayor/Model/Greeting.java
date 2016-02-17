package plazamayor;

import java.util.Random;

public class Greeting {

    private long id;
    private String content;
    public int asdf = (new Random()).nextInt(10);

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSomeStuff() {
        return id + " - " + content;
    }

    @Override
    public String toString() {
        return "chicken";
    }
}
