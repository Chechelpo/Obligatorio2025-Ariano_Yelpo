package Domain;

import Interfaces.MyList;
import Semantics.NotNullInteger;
import Utils.SimpleLinkedList.MyLinkedList;

import java.util.List;

public class Usuario {
    private final MyLinkedList<Review> reviews;
    private NotNullInteger id;

    //Complete Constructor
    public Usuario(NotNullInteger id) {
        this.id = id;
        this.reviews = new MyLinkedList<>();
    }

    //Getters and Setters
    public NotNullInteger getId() {
        return id;
    }

    public void setId(NotNullInteger id) {
        this.id = id;
    }

    public MyList<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
