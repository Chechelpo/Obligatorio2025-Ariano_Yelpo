package Domain;

import java.util.List;

public class ColeccionConIngresos implements Comparable<ColeccionConIngresos> {
    private final int idColeccion;
    private final String nombre;
    private final int ingresos;
    private final List<Integer> idsPeliculas;

    public ColeccionConIngresos(int idColeccion, String nombre, int ingresos, List<Integer> idsPeliculas) {
        this.idColeccion = idColeccion;
        this.nombre = nombre;
        this.ingresos = ingresos;
        this.idsPeliculas = idsPeliculas;
    }

    public int getIdColeccion() {
        return idColeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIngresos() {
        return ingresos;
    }

    public int getCantidadPeliculas() {
        return idsPeliculas.size();
    }

    public List<Integer> getIdsPeliculas() {
        return idsPeliculas;
    }

    @Override
    public int compareTo(ColeccionConIngresos otra) {
        return Integer.compare(otra.ingresos, this.ingresos); // descendente
    }
}

