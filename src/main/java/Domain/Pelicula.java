package Domain;

import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTableCerrado.HashCerrado;
import Utils.SimpleLinkedList.MyLinkedList;

public class Pelicula {
    private final NotNullInteger id;
    private final NotBlankString title;
    private final int budget;
    private NotBlankString originalLanguage;
    private MyList<Review> reviews;
    private final HashCerrado<String,Boolean> generos ;
    private int revenue;
    private int income;

    //Complete Constructor
    public Pelicula(NotNullInteger id, NotBlankString title, int budget, NotBlankString originalLanguage, int revenue, int ingresos) {
        this.id = id;
        this.title = title;
        this.budget = budget;
        this.originalLanguage = originalLanguage;
        this.revenue = revenue;
        this.income = ingresos;
        this.reviews = new MyLinkedList<>();
        this.generos = new HashCerrado<>(5000);
    }

    //Getters and Setters

    public HashCerrado<String, Boolean> getGenero() {
        return generos;
    }

    public NotNullInteger getId() {
        return id;
    }

    public NotBlankString getTitle() {
        return title;
    }

    public int getBudget() {
        return budget;
    }

    public NotBlankString getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(NotBlankString originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public MyList<Review> getReviews() {
        return reviews;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

//Functions
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    public void addGenre(String genre) {
        generos.put(genre, true);
    }
}
