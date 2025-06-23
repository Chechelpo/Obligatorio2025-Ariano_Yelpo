package Domain;

import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTableCerrado.HashCerrado;

public class Pelicula {
    private final NotNullInteger id;
    private final NotBlankString title;
    private NotBlankString originalLanguage;
    private final HashCerrado<String, Boolean> generos;
    private final int income;

    //Complete Constructor
    public Pelicula(NotNullInteger id, NotBlankString title, int budget, NotBlankString originalLanguage, int revenue, int ingresos) {
        this.id = id;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.income = budget - revenue;
        this.generos = new HashCerrado<>(15);
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


    public String getOriginalLanguage() {
        return originalLanguage.getValue();
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = new NotBlankString(originalLanguage);
    }


    public int getIncome() {
        return income;
    }


    //Functions
    public void addGenre(String genre) {
        generos.put(genre, true);
    }
}
