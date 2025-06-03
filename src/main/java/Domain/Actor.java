package Domain;

import java.util.List;

public class Actor {
    private String name;
    private List<Pelicula> participatedIn;

    //Empty Constructor
    public Actor() {
    }

    //Complete Constructor
    public Actor(String name, List<Pelicula> participatedIn) {
        this.name = name;
        this.participatedIn = participatedIn;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pelicula> getParticipatedIn() {
        return participatedIn;
    }

    public void setParticipatedIn(List<Pelicula> participatedIn) {
        this.participatedIn = participatedIn;
    }
}
