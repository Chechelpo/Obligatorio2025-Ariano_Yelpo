package Domain;

import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTableCerrado.MyHashCerrado;

public class Pelicula {
    private final NotNullInteger id;
    private final NotBlankString title;
    private NotBlankString originalLanguage;
    private final MyHashCerrado<String, Boolean> generos;
    private final int income;

    //Complete Constructor
    public Pelicula(NotNullInteger id, NotBlankString title, int budget, NotBlankString originalLanguage, int revenue) {
        this.id = id;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.income = revenue; //Ingresos, no beneficios mamita queria
        this.generos = new MyHashCerrado<>(15);
    }

    //Getters and Setters

    public MyHashCerrado<String, Boolean> getGenero() {
        return generos;
    }

    public NotNullInteger getId() {
        return id;
    }

    public NotBlankString getTitle() {
        return title;
    }

    public String getOriginalLanguage() {
        return originalLanguage.getValue();
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = new NotBlankString(originalLanguage);
    }


    public int getIncome() {
        if (income < 0) {
            System.err.println("⚠ Película con ingreso negativo: " + title.getValue() + " - " + income);
            return 0;
        }return income;

    }


    //Functions
    public void addGenre(String genre) {
        generos.put(genre, true);
    }
}
