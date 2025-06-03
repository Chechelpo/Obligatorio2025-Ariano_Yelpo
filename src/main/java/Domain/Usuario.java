package Domain;

import Semantics.NotNullInteger;

import java.util.List;

public class Usuario {
    private NotNullInteger id;
    private List<Review> reviews;

    //Empty Constructor
    public Usuario() {
    }

    //Complete Constructor
    public Usuario(NotNullInteger id, List<Review> reviews) {
        this.id = id;
        this.reviews = reviews;
    }

    //Getters and Setters
    public NotNullInteger getId() {
        return id;
    }

    public void setId(NotNullInteger id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
