package Domain;

import Interfaces.MyHashTable;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;

public class ActorStats implements Comparable<ActorStats> {
    private String nombre;
    private int cantidadPeliculas;
    private int totalCalificaciones;
    private final MyHashTable<NotNullInteger, Boolean> peliculasVistas;

    public ActorStats(String nombre) {
        this.nombre = nombre;
        this.cantidadPeliculas = 0;
        this.totalCalificaciones = 0;
        this.peliculasVistas = new HashTable<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadPeliculas() {
        return cantidadPeliculas;
    }

    public void setCantidadPeliculas(int cantidadPeliculas) {
        this.cantidadPeliculas = cantidadPeliculas;
    }

    public int getTotalCalificaciones() {
        return totalCalificaciones;
    }

    public void setTotalCalificaciones(int totalCalificaciones) {
        this.totalCalificaciones = totalCalificaciones;
    }

    public MyHashTable<NotNullInteger, Boolean> getPeliculasVistas() {
        return peliculasVistas;
    }

    // Ordenamiento por cantidad de películas, luego por calificaciones
    @Override
    public int compareTo(ActorStats otro) {
        int cmp = Integer.compare(otro.cantidadPeliculas, this.cantidadPeliculas);
        if (cmp == 0) {
            cmp = Integer.compare(otro.totalCalificaciones, this.totalCalificaciones);
        }
        return cmp;
    }
}

