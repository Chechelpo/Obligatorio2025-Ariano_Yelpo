package Domain;

import Semantics.NotBlankString;
import Semantics.NotNullInteger;

import java.util.List;

public class Pelicula {
    private NotNullInteger id;
    private NotBlankString title;
    private int budget;
    private NotBlankString originalLanguage;
    private List<Review> reviews; //Cambiar esto despues
    private int revenew;
    private int income;

    //Empty Constructor
    public Pelicula() {
    }

    //Complete Constructor
    public Pelicula(NotNullInteger id, NotBlankString title, int budget, NotBlankString originalLanguage, int revenew, int ingresos) {
        this.id = id;
        this.title = title;
        this.budget = budget;
        this.originalLanguage = originalLanguage;
        this.revenew = revenew;
        this.income = ingresos;
    }

    //Getters and Setters
    public NotNullInteger getId() {
        return id;
    }

    public void setId(NotNullInteger id) {
        this.id = id;
    }

    public NotBlankString getTitle() {
        return title;
    }

    public void setTitle(NotBlankString title) {
        this.title = title;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public NotBlankString getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(NotBlankString originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getRevenew() {
        return revenew;
    }

    public void setRevenew(int revenew) {
        this.revenew = revenew;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

//Functions
}
