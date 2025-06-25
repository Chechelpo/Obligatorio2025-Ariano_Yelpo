package Utils;

import Interfaces.MyList;

public class ColeccionConIngresos implements Comparable<ColeccionConIngresos> {
    private final int idColeccion;
    private final String nombre;
    private final int ingresos;
    private final MyList<Integer> idsPeliculas;

    public ColeccionConIngresos(int idColeccion, String nombre, int ingresos, MyList<Integer> idsPeliculas) {
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
        return idsPeliculas.getSize();
    }

    public MyList<Integer> getIdsPeliculas() {
        return idsPeliculas;
    }

    @Override
    public int compareTo(ColeccionConIngresos otra) {
        return Integer.compare(this.ingresos, otra.ingresos); // descendente
    }
}

