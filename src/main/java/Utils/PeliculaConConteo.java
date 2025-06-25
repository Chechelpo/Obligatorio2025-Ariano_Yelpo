package Utils;

import Domain.Pelicula;

public class PeliculaConConteo implements Comparable<PeliculaConConteo> {
    private final Pelicula pelicula;
    private final int conteo;

    public PeliculaConConteo(Pelicula pelicula, int conteo) {
        this.pelicula = pelicula;
        this.conteo = conteo;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public int getConteo() {
        return conteo;
    }

    @Override
    public int compareTo(PeliculaConConteo otra) {
        // Orden descendente: más reviews primero
        return Integer.compare(this.conteo, otra.conteo);
    }
}

