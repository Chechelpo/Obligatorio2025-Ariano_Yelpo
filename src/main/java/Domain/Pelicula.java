package Domain;

import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;
import Utils.HashTableCerrado.HashCerrado;
import Utils.SimpleLinkedList.MyLinkedList;

public class Pelicula {
    private final NotNullInteger id;
    private final NotBlankString title;
    private NotBlankString originalLanguage;
    private final HashCerrado<String, Boolean> generos;
    private final int income;

    //Complete Constructor
    public Pelicula(NotNullInteger id, NotBlankString title, int budget, NotBlankString originalLanguage, int revenue) {
        this.id = id;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.income = budget - revenue;
        this.generos = new HashCerrado<>(15);
    }

    //Getters and setters
    public HashCerrado<String, Boolean> getGenero() {
        return generos;
    }

    public NotNullInteger getId() {
        return id;
    }

    public NotBlankString getTitle() {
        return title;
    }


    public NotBlankString getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(NotBlankString originalLanguage) {
        this.originalLanguage = originalLanguage;
    }


    public int getIncome() {
        return income;
    }


    //Functions
    public void addGenre(String genre) {
        generos.put(genre, true);
    }
}